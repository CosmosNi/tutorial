package com.cosmos.cache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-11 15:10
 * @Modified By：
 */

/**
 * 缓存一个future而不是一个值，带来了缓存污染的可能性。
 * 如果一个计算被取消或者失败，未来尝试对这个值进行计算都会表现为取消或者失败。
 */
public class MemoizerFutureTask<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> computable;

    static final CyclicBarrier barrier = new CyclicBarrier(16);

    public MemoizerFutureTask(Computable<A, V> computable) {
        this.computable = computable;
    }

    public int getSize() {
        return cache.size();
    }

    public V compute(final A arg) throws InterruptedException, ExecutionException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                public V call() throws Exception {

                    return computable.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            //此步先将Future加入到cache中，然后在做计算，这样当新的线程进来后，
            //如果请求的是其他线程正在计算的结果，他会耐心的等待。
            //缺陷：两根线程同时到达if代码块内部
            cache.put(arg, f);
            ft.run();
        }


        return f.get();

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, BrokenBarrierException {

        final MemoizerFutureTask memoizerl = new MemoizerFutureTask(new ExpensiveFunction());


    }
}
