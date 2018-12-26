package com.cosmos.design.strategy;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-25 08:43
 * @Modified By：
 */
public class Calculator {
    private Strategy strategy;

    public Calculator(Strategy strategy) {
        this.strategy = strategy;
    }

    public int operation(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
