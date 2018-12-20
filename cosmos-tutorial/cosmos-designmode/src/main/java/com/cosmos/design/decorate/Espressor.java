package com.cosmos.design.decorate;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 浓咖啡
 * @Date: Create in 2018-12-20 10:11
 * @Modified By：
 */
public class Espressor extends Beverage {

    public Espressor() {
        super.description = "浓咖啡";
    }

    @Override
    public double cost() {
        return 10.11;
    }
}
