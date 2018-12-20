package com.cosmos.design.iterator;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 09:21
 * @Modified By：
 */
public class Menu extends MenuComponent {

    private ArrayList<MenuComponent> components = new ArrayList<>();

    private String name;

    private String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        components.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        components.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return components.get(i);
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
        return super.getPrice();
    }


    @Override
    public void print() {
        System.out.println("菜单名：" + name + "  " + "菜单描述：" + description);
        Iterator iterator = components.iterator();

        System.out.println("子菜单详解:");
        System.out.println("----------------------------------------------------");
        while (iterator.hasNext()) {
            MenuComponent component = (MenuComponent) iterator.next();
            component.print();
            System.out.println("##################################################");
        }
    }

    @Override
    public Iterator createIterator() {
        return new CompositeIterator(components.iterator());
    }
}
