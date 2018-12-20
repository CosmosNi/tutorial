package com.cosmos.design.factory.abstractFactory;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 生产一个pizza
 * @Date: Create in 2018-12-14 15:15
 * @Modified By：
 */
public abstract class Pizza {
    private String name;
    private Dough dough;


    abstract void prepare();

    abstract void complete();

    public void bake() {
        System.out.println("350度下烘烤pizza");
    }

    public void cut() {
        System.out.println("切割pizza");
    }

    public void box() {
        System.out.println("pizza装盘");
    }

}
