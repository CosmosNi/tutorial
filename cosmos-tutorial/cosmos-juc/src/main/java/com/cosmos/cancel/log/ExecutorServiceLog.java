package com.cosmos.cancel.log;

import org.omg.CORBA.TIMEOUT;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: shutdown() 方法在终止前允许执行以前提交的任务，
 * 而 shutdownNow() 方法阻止等待任务启动并试图停止当前正在执行的任务。
 * @Date: Create in 2018-12-14 13:47
 * @Modified By：
 */
public class ExecutorServiceLog {
    private final BlockingQueue<String> queue;
    private final PrintWriter printWriter;
    private ExecutorService exec = newSingleThreadExecutor();
    public static final ExecutorService logWrite = newFixedThreadPool(10);


    public ExecutorServiceLog() throws IOException {
        queue = new LinkedBlockingQueue<String>();
        printWriter = new PrintWriter(new FileWriter("pw2.txt"), true);
    }

    public void stop() {

        try {
            System.out.println("关闭线程池");
            exec.shutdown();
            exec.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }
    }

    public void log(final String msg) {
        exec.execute(new Runnable() {
            Random random = new Random();

            @Override
            public void run() {
                try {
                    int longTime = (random.nextInt(10) + 1) * 1000;
                    System.out.println(longTime);
                    Thread.sleep(longTime);
                    printWriter.println(msg + longTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        final ExecutorServiceLog executorServiceLog = new ExecutorServiceLog();
        final AtomicInteger ato = new AtomicInteger(0);

        for (int i = 0; i < 5; i++) {

            executorServiceLog.log(Thread.currentThread().getName() + "  " + ato.getAndIncrement());
        }
        executorServiceLog.stop();
        for (int i = 0; i < 5; i++) {

            executorServiceLog.log(Thread.currentThread().getName() + "  " + ato.getAndIncrement());
        }
    }
}
