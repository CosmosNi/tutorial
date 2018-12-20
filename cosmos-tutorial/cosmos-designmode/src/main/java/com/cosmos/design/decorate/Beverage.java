package com.cosmos.design.decorate;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 10:06
 * @Modified By：
 */
public abstract class Beverage {

    public String description;

    public abstract double cost();

    public String getDescription() {
        return description;
    }
}
