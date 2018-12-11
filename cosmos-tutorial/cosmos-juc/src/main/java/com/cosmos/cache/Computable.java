package com.cosmos.cache;

import java.util.concurrent.ExecutionException;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-10 23:03
 * @Modified By：
 */
public interface Computable<A,V> {
    V compute(A arg) throws InterruptedException, ExecutionException;
}
