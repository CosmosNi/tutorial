package com.cosmos.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description:
 * 类似于闭锁。与闭锁不同之处在于，所有的线程必须同时到达关卡点，才能继续处理。
 * 闭锁等待的是事件；而同步屏障等待的是其他的线程。
 * 比如：可将一个任务分割成多个子部分，然后再整合
 * @Date: Create in 2018-12-10 22:40
 * @Modified By：
 */
public class CyclicBarrierTest {
    static final CyclicBarrier barrier = new CyclicBarrier(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    System.out.println("正在执行任务....");

                    try {
                        Thread.sleep(2000);
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("结束任务完毕....");
                }
            }).start();
        }
    }
}
