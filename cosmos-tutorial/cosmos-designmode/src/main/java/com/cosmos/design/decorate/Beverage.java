package com.cosmos.design.decorate;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 10:06
 * @Modified By：
 */
public abstract class Beverage {
    /**
     * 饮料描述
     */
    public String description;

    /**
     * 价格，此处需要子类具体实现价格
     * @return
     */
    public abstract double cost();

    public String getDescription() {
        return description;
    }
}
