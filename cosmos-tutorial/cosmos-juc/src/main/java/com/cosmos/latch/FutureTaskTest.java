package com.cosmos.latch;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 闭锁的另一个实现 FutureTask
 * 通过获取Callable返回值的方式。等价于一个可携带结果的Runable，并且有三个状态，
 * 等待，运行和完成。完成包括所有计算以任意的方式结束，包括正常结束，取消和异常。一旦FutureTask
 * 进入完成状态，他会永远停止在这个状态上
 * @Date: Create in 2018-12-10 21:30
 * @Modified By：
 */
public class FutureTaskTest {

    private final FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
        public String call() throws Exception {
            System.out.println("执行任务");
            Thread.sleep(2000);
            System.out.println("结束任务");
            return "success";
        }
    });
    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public String get() {
        try {
            /**
             * 依赖于任务的状态，如果他已经完成,get可以立刻得到返回结果，
             * 否则会被堵塞直到任务转入完成状态，然后会返回结果或者抛出异常。
             * FutureTask把计算的结果从运行计算的线程传送到需要这个结果的线程；FutureTask的
             * 规约保证了这种传递建立在结果的安全发布基础之上
             */
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            //ExecutionException异常的原因
            /**
             * 1.Callacle抛出的受检查的异常，或是RuntimeException，或是error
             */
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws InterruptedException {
        final FutureTaskTest futureTaskTest = new FutureTaskTest();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {

//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    System.out.println(futureTaskTest.get());
                }
            }).start();

        }
        //Thread.sleep(2000);
        //释放锁
        futureTaskTest.start();
    }
}
