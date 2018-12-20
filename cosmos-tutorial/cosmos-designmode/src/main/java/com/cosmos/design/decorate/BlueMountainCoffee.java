package com.cosmos.design.decorate;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 10:14
 * @Modified By：
 */
public class BlueMountainCoffee extends Beverage {
    public BlueMountainCoffee() {
        super.description = "蓝山咖啡";
    }

    @Override
    public double cost() {
        return 12.01;
    }
}
