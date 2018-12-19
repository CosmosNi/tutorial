package com.cosmos.cancel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.*;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 这里有一个示例，用来限时运行任务，在规定的时间内，不论程序是否响应中断终止程序，还是没有响应到中断，
 * 我们都可以获取到通过调用这个方法获取结果，
 * 也就是可以再规定的时间内获取到处理结果，对结果如何处理，那就可以自己定义。
 * 无论join是否成功，在java存储模型中都会相应存在内存可见性，但是join本身不会返回表示它成功与否的任何状态。
 *
 * @Date: Create in 2018-12-14 09:01
 * @Modified By：
 */
public class CancelWithJoin {

    private static ScheduledExecutorService scheduledThreadPool = newSingleThreadScheduledExecutor();

    public static void launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new RuntimeException(t);
        }
    }

    public static void timeRun(final Runnable runnable, long timeout, TimeUnit unit) throws Throwable {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            public void run() {
                try {
                    runnable.run();
                } catch (Throwable t) {
                    System.out.println("抛出Throwable");
                    this.t = t;
                }
            }

            void rethrow() throws Throwable {
                if (t != null) {
                    throw t;
                }
            }
        }
        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        //定时任务，用来打断taskThread线程
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("线程打断");
                taskThread.interrupt();
            }
        }, timeout, unit);
        //等待线程的终止，用来应对即使任务不响应中断，限定时间一到，最后仍可以抛出异常到这个方法的调用者
        //thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。
        // 比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
        taskThread.join(unit.toMillis(timeout));
        //执行线程打断
        System.out.println("抛出Throwable，用来打断线程");
        task.rethrow();

    }

    public static void main(String[] args) {
        try {
            CancelWithJoin.timeRun(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("启动线程");
                }
            }, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
