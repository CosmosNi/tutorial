package com.cosmos.design.iterator;

import java.util.Iterator;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 09:34
 * @Modified By：
 */
public class MenuItem extends MenuComponent {

    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        super.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        super.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return super.getChild(i);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public double getPrice() {
        return this.price;
    }


    @Override
    public void print() {
        System.out.println("菜名：" + name);
        System.out.println("形容：" + description);
        System.out.println("价格：" + price);
    }

    @Override
    public Iterator createIterator() {
        return new NullIterator();
    }
}
