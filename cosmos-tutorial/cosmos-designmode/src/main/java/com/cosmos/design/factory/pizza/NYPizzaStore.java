package com.cosmos.design.factory.pizza;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 15:45
 * @Modified By：
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza choosePizza(String type) {
        DoughIngredientFactory doughIngredientFactory = new DoughIngredientFactoryImpl();

        if ("thin".equals(type)) {
            return new NYThinCrustPizza(doughIngredientFactory);
        }
        if ("thick".equals(type)) {
            return new NYThickCrustPizza(doughIngredientFactory);
        }
        return null;
    }
}
