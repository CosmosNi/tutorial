package com.cosmos.cancel.newTaskFor;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 10:07
 * @Modified By：
 */
public interface CancellableTask<T> extends Callable<T> {

    void cancel();

    RunnableFuture<T> newTask();
}
