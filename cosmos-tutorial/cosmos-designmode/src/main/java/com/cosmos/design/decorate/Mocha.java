package com.cosmos.design.decorate;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 10:16
 * @Modified By：
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + 2.0;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + "加摩卡";
    }
}
