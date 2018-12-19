package com.cosmos.design.factory.pizza;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 15:30
 * @Modified By：
 */
public class DoughIngredientFactoryImpl implements DoughIngredientFactory {

    public Dough chooseDough(String type) {
        if (type.equals("thick")) {
            return new ThickCrustDough();
        }
        if (type.equals("thin")) {
            return new ThinCrustDough();
        }
        return null;
    }
}
