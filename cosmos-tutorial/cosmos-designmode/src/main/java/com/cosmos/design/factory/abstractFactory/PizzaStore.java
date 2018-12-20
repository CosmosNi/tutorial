package com.cosmos.design.factory.abstractFactory;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 15:37
 * @Modified By：
 */
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza = choosePizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        pizza.complete();
        return pizza;
    }

    public abstract Pizza choosePizza(String type);
}
