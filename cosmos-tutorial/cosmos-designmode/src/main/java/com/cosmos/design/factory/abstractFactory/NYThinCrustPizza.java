package com.cosmos.design.factory.abstractFactory;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 15:33
 * @Modified By：
 */
public class NYThinCrustPizza extends Pizza {

    DoughIngredientFactory doughIngredientFactory;

    public NYThinCrustPizza(DoughIngredientFactory doughIngredientFactory) {
        this.doughIngredientFactory = doughIngredientFactory;
    }

    @Override
    void prepare() {
        Dough dough = doughIngredientFactory.chooseDough("thin");
        dough.chooseDough();
    }


    @Override
    void complete() {
        System.out.println("纽约薄脆饼pizza制作完成");
    }

}
