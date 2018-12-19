package com.cosmos.executor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.*;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: //当所有任务完成时
 * 调用线程被中断时或者超过限定时间，invokeAll都会返回结果。超过时限后，任何尚未完成的任务都会被取消，作为invokeAll
 * 的返回值，每个人物要么正常的完后才能，要么被取消
 * @Date: Create in 2018-12-13 21:40
 * @Modified By：
 */
public class InvokeAllTest {

    private static ExecutorService mExecutor = newFixedThreadPool(5);

    private class CosmosTask implements Callable<String> {
        private String people;

        public CosmosTask(String people) {
            this.people = people;
        }

        public String timeOutCanel() {
            return people + "任务超时";
        }

        public String failCanel() {
            return people + "任务异常结束";
        }

        @Override
        public String call() throws Exception {
            Random r = new Random();
            long time = (r.nextInt(10) + 1) * 1000;
            System.out.println("当前线程:" + people + "等待被处理,预计耗时+" + time / 1000);

            Thread.sleep(time);
            return "当前线程:" + people + "处理时长" + time / 1000 + "s";
        }


    }

    public List<String> taskCanel() {
        List<CosmosTask> tasks = new ArrayList<CosmosTask>();
        for (int i = 0; i < 20; i++) {
            tasks.add(new CosmosTask("people" + i));
        }
        List<Future<String>> cannelFuture = null;
        try {
            //将多个任务提交到ExecutorService
            //控制15s内未完成任务取消
            cannelFuture = mExecutor.invokeAll(tasks, 15, TimeUnit.SECONDS);
            //不控制时长
           // cannelFuture = mExecutor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> totalComplete = new ArrayList<String>(tasks.size());
        Iterator<CosmosTask> cancelIterator = tasks.iterator();
        for (Future<String> future :
                cannelFuture) {
            CosmosTask task = cancelIterator.next();
            try {
                totalComplete.add(future.get());
            } catch (InterruptedException e) {
                totalComplete.add(task.failCanel());
            } catch (ExecutionException e) {
                totalComplete.add(task.failCanel());
            } catch (CancellationException e) {
                totalComplete.add(task.timeOutCanel());
            }
        }
        mExecutor.shutdown();
        return totalComplete;
    }

    public static void main(String[] args) {
        InvokeAllTest test = new InvokeAllTest();
        List<String> list = test.taskCanel();
        for (String str :
                list) {
            System.out.println(str);
        }
    }
}



