package com.cosmos.cancel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 取消任务案例
 * @Date: Create in 2018-12-13 22:30
 * @Modified By：
 */
public class PrimeGenerator implements Runnable {
    private final List<BigInteger> primes = new ArrayList<BigInteger>();
    private volatile boolean cancelled;

    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }

    public static void main(String[] args) {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            generator.cancel();
        }
        System.out.println(generator.get());
    }
}
