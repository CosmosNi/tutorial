package com.cosmos.semphore;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 计数信号量，用来控制能够同时访问某特定资源的活动的数量
 * 计数信号量可以用来实现资源池或者给一个容器限定边界。
 * 一个Semaphore管理一个有效的许可，许可的除湿量通过构造函数传递给semaphore
 * 活动能够获得许可（只要还有剩余许可），并在使用之后释放许可，如果没有可用的许可
 * 则acquire会被堵塞，直到有可用的为止
 * 类似数据库连接池
 * @Date: Create in 2018-12-10 22:13
 * @Modified By：
 */
public class BoundHashSet<T> {

    private final Set<T> set;
    private final Semaphore sem;


    public BoundHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        this.sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            System.out.println("新增对象" + o.toString());
            return wasAdded;
        } finally {
            if (!wasAdded) {
                //System.out.println("释放对象" + o.toString());
                //sem.release();
            }
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        System.out.println("移除对象" + o.toString());
        if (!wasRemoved) {
            System.out.println("释放对象" + o.toString());
            sem.release();
        }
        return wasRemoved;
    }

    public static void main(String[] args) throws InterruptedException {
        final BoundHashSet<String> boundHashSet = new BoundHashSet<String>(2);
        boundHashSet.add("123");

        boundHashSet.add("145");
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("等待.....");
                    boundHashSet.add("1453");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        Thread.sleep(2000);
        boundHashSet.remove(123);
        while(true){
            synchronized(boundHashSet){
                System.out.println( "2.无限期等待中..." );
                boundHashSet.wait(); //等待，直到其它线程调用 lock.notify()

            }
        }

    }
}
