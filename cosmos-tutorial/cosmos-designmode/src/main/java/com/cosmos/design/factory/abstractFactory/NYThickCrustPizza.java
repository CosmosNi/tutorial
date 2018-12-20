package com.cosmos.design.factory.abstractFactory;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 15:33
 * @Modified By：
 */
public class NYThickCrustPizza extends Pizza {

    private DoughIngredientFactory doughIngredientFactory;

    public NYThickCrustPizza(DoughIngredientFactory doughIngredientFactory) {
        this.doughIngredientFactory = doughIngredientFactory;
    }

    @Override
    void complete() {
        System.out.println("纽约厚脆饼pizza制作完成");
    }


    @Override
    void prepare() {

        Dough dough = doughIngredientFactory.chooseDough("thick");
        dough.chooseDough();
    }


}
