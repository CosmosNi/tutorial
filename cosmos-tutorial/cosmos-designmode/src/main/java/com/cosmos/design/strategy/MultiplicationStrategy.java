package com.cosmos.design.strategy;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-19 20:58
 * @Modified By：
 */
public class MultiplicationStrategy implements Strategy {
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
