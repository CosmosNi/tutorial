package com.cosmos.cancel.log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 13:00
 * @Modified By：
 */
public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoogerThread loggerThread;

    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingQueue<String>();
        this.loggerThread = new LoogerThread((PrintWriter) writer);
    }

    public void start() {
        loggerThread.start();
    }

    public void log(String msg) throws InterruptedException {
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
                    writer.println(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final LogWriter log = new LogWriter(new PrintWriter(new FileWriter("pw2.txt"), true));
        final AtomicInteger ato = new AtomicInteger(0);
        log.start();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.log(Thread.currentThread().getName()+"  "+ ato.getAndIncrement());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }
}
