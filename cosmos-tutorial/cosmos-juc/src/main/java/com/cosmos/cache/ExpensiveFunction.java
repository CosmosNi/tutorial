package com.cosmos.cache;

import java.math.BigDecimal;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-10 23:04
 * @Modified By：
 */
public class ExpensiveFunction implements Computable<String, BigDecimal>{
    public BigDecimal compute(String arg) throws InterruptedException {
        return new BigDecimal(arg);
    }
}
