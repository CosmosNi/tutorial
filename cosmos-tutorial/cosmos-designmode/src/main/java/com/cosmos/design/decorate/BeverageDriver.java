package com.cosmos.design.decorate;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 10:19
 * @Modified By：
 */
public class BeverageDriver {

    public static void main(String[] args) {
        Beverage beverage = new Espressor();
        beverage = new Mocha(beverage);
        beverage = new SemiSweet(beverage);

        System.out.println(beverage.getDescription());
        System.out.println(beverage.cost());
    }
}
