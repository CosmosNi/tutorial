package com.cosmos.cancel;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 09:42
 * @Modified By：
 */
public class CancelWithFuture {
    private static ExecutorService mExecutor = newFixedThreadPool(5);

    public static void timeRun(final Runnable runnable, long timeout, TimeUnit unit) {
        Future<?> task = mExecutor.submit(runnable);
        try {
            task.get(timeout, unit);
        } catch (InterruptedException e) {
            System.out.println("线程被打断");
            task.cancel(true);

        } catch (ExecutionException e) {
            System.out.println(e.getCause());
        } catch (TimeoutException e) {
            System.out.println("超时");
            task.cancel(true);
        } finally {
            task.cancel(true);
            mExecutor.shutdown();
        }
    }

    public static void main(String[] args) {
        CancelWithFuture.timeRun(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行任务");
            }
        }, 5, TimeUnit.SECONDS);
    }
}
