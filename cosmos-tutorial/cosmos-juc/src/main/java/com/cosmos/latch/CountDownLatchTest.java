package com.cosmos.latch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 闭锁的作用：
 * 1.确保一个计算不被执行，直到它需要的资源初始化。
 * 2.确保一个服务不会开始，直到它依赖的其他服务都已经开始
 * 3.等待，直到活动的所有部分都为继续处理做好充分准备
 *
 * CountDownLatch是一个灵活的闭锁实现。允许一个或多个线程等待一个事件集的发生。闭锁状态包括一个计数器，初始化为一个整数
 * 用来表现需要等待的事件数。countDown方法针对计数器做减操作，表示一个事件已经发生了，
 * 而await方法等待计数器达到零，此时所有需要等待的事件都已经发生。
 * 如果计数器入口时值为非零，await会一直堵塞直到计数器为零，或者等待线程中断以及超时。
 *
 * @Date: Create in 2018-12-10 20:59
 * @Modified By：
 */
public class CountDownLatchTest {
    public static long timeTask(int nThread, final Runnable task) throws InterruptedException {
        //开始阀门
        final CountDownLatch startGet = new CountDownLatch(1);
        //结束阀门
        final CountDownLatch endGet = new CountDownLatch(nThread);
        for (int i = 0; i < nThread; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        //等待
                        startGet.await();
                        System.out.println("开始执行任务:" + Thread.currentThread().getName());
                        task.run();
                        System.out.println("结束执行任务:" + Thread.currentThread().getName());
                        endGet.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        long start = System.nanoTime();
        //释放所有线程
        startGet.countDown();
        //防止线程执行下去，只有当所有线程执行完毕才能释放锁
        endGet.await();
        long end = System.nanoTime();
        System.out.println(end - start);
        return end - start;
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatchTest.timeTask(10, new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
