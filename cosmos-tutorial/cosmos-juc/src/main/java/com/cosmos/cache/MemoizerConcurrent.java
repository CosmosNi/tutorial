package com.cosmos.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-10 23:05
 * @Modified By：
 */
public class MemoizerConcurrent<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A, V> computable;

    public MemoizerConcurrent(Computable<A, V> computable) {
        this.computable = computable;
    }


    /**
     * 如果一个线程启动了一个开销很大的计算，其他线程并不知道这个计算正在进行中
     * 就有可能又会重复计算。
     * @param arg
     * @return
     * @throws InterruptedException
     */
    public V compute(A arg) throws InterruptedException, ExecutionException {
        V result = cache.get(arg);
        System.out.println("读取缓存中数据");
        if (result == null) {
            System.out.println("缓存中不存在数据！重新获取");
            result = computable.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MemoizerConcurrent memoizerl = new MemoizerConcurrent(new ExpensiveFunction());
        memoizerl.compute("123");
        memoizerl.compute("123");
    }

}
