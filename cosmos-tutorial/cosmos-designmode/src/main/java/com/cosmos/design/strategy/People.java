package com.cosmos.design.strategy;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-19 21:00
 * @Modified By：
 */
public class People {

    private Strategy strategy;

    public People(Strategy strategy) {
        this.strategy = strategy;
    }

    public int operation(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
