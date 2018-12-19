package com.cosmos.cancel.log;

import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 原子化的检查关闭，并有条件的地政记录获得提交信息权利的计数器。
 * @Date: Create in 2018-12-14 13:26
 * @Modified By：
 */
public class LogService {
    private final BlockingQueue<String> queue;
    private final LoogerThread loogerThread;
    private final PrintWriter printWriter;
    private boolean isShutdown;
    private int reservations;


    public LogService() throws IOException {
        queue = new LinkedBlockingQueue<String>();
        printWriter = new PrintWriter(new FileWriter("pw2.txt"), true);
        loogerThread = new LoogerThread(printWriter);
    }

    public void start() {
        loogerThread.start();
    }

    public void stop() {
        printWriter.println("准备停止线程");
        synchronized (this) {
            isShutdown = true;
        }
        loogerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        //做关闭标识，防止信息进一步被提交进来
        synchronized (this) {
            if (isShutdown) {
                throw new IllegalStateException("线程被关闭");
            }
            ++reservations;
        }
        queue.put(msg);

    }


    private class LoogerThread extends Thread {

        private final PrintWriter writer;

        public LoogerThread(PrintWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (LogService.class) {
                        if (isShutdown && reservations == 0) {
                            break;
                        }
                        synchronized (LogService.class) {
                            --reservations;
                        }
                        writer.println(queue.take());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        final LogService logService = new LogService();
        logService.start();
        final AtomicInteger ato = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        logService.log(Thread.currentThread().getName() + "  " + ato.getAndIncrement());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        logService.stop();

    }
}
