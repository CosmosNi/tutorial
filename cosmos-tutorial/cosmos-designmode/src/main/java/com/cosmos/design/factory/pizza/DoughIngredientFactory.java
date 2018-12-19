package com.cosmos.design.factory.pizza;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 原材料工厂
 * @Date: Create in 2018-12-14 15:27
 * @Modified By：
 */
public interface DoughIngredientFactory {
    public Dough chooseDough(String type);
}
