package com.cosmos.design.factory.pizza;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 薄脆面团
 * @Date: Create in 2018-12-14 15:21
 * @Modified By：
 */
public class ThickCrustDough implements Dough {

    public void chooseDough() {
        System.out.println("使用厚面团制作pizza！");
    }
}
