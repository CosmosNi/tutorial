package com.cosmos.design.factory.abstractFactory;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 抽象工厂模式提供一个接口，用于创建相关或依赖对象的家族，而不需要明确指定具体类
 * @Date: Create in 2018-12-14 15:51
 * @Modified By：
 */
public class PizzaDriver {

    public static void main(String[] args) {
        PizzaStore ps = new NYPizzaStore();

        Pizza pizza = ps.orderPizza("thick");
    }
}
