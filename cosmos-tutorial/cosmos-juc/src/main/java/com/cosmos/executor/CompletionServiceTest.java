package com.cosmos.executor;

import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description:  为每一个耗时任务创建一个独立的任务，并在线程池中执行他们。将
 *                 顺序的过程转换为并行的，减少所有任务的总时间。
 *                 CompletionService整合了Executor和BlockQueue的功能。你可以将Callable任务提交给它
 *                 去执行，然后使用类似于队列中的take和poll方法，在结果完整可用时获取这个结果，就想一个
 *                 打包的Future。
 * @Date: Create in 2018-12-13 15:02
 * @Modified By：
 */
public class CompletionServiceTest {
    private final ExecutorService executorService;

    public CompletionServiceTest(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void run() throws InterruptedException, ExecutionException {
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);
        for (int i = 0; i < 20; i++) {
            completionService.submit(new Callable<String>() {
                public String call() throws Exception {
                    Thread.sleep(10000);
                    return Thread.currentThread().getName() + " 执行到此处";
                }
            });
        }
        System.out.println("主线程执行到此处");

        for (int i = 0; i < 20; i++) {
            Future<String> f = completionService.take();
            System.out.println(f.get());
        }
        executorService.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletionServiceTest completionServiceTest = new CompletionServiceTest(newFixedThreadPool(5));
        completionServiceTest.run();
    }
}

