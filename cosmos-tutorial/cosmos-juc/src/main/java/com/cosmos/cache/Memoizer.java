package com.cosmos.cache;

import java.util.concurrent.*;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 最优cache实现方法
 * @Date: Create in 2018-12-11 15:59
 * @Modified By：
 */
public class Memoizer<A, V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> computable;

    public Memoizer(Computable computable) {
        this.computable = computable;
    }

    public V compute(final A arg) {
        while (true) {
            Future<V> f = cache.get(arg);
            if (f == null) {
                Callable<V> eval = new Callable<V>() {
                    public V call() throws Exception {
                        return computable.compute(arg);
                    }
                };
                FutureTask<V> futureTask = new FutureTask<V>(eval);
                //优化MemoizerFutureTask，利用ConcurrentHashMap再次校验是否存在，避免重复计算
                f = cache.putIfAbsent(arg, futureTask);
                if (f == null) {
                    f = futureTask;
                    futureTask.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e) {
               cache.remove(arg);
        } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
