package com.cosmos.design.factory.pizza;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 15:51
 * @Modified By：
 */
public class PizzaDriver {

    public static void main(String[] args) {
        PizzaStore ps = new NYPizzaStore();

        Pizza pizza = ps.orderPizza("thick");
    }
}
