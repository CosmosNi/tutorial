package com.cosmos.design.iterator;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 组合模式+迭代器模式
 * @Modified By：
 */
public class MenuDriver {
    public static void main(String[] args) {

        MenuComponent allMenu = new Menu("总菜单", "这是一份总菜单");

        MenuComponent chuan = new Menu("川菜", "这是一份川菜菜单");
        MenuComponent yue = new Menu("粤菜", "这是一份粤菜菜单");
        MenuComponent su = new Menu("苏邦菜", "这是一份苏邦菜菜单");

        MenuComponent hotPot = new MenuItem("四川火锅", "这是一份四川火锅", 100.0);
        MenuComponent maoXueWang = new MenuItem("毛血旺", "这是一份毛血旺", 10.0);
        chuan.add(hotPot);
        chuan.add(maoXueWang);

        MenuComponent kaoRuZhu = new MenuItem("烤乳猪", "这是一份烤乳猪", 500.0);
        MenuComponent laoHuoLiangTang = new MenuItem("老火靓汤", "这是一份老火靓汤", 20.0);
        yue.add(kaoRuZhu);
        yue.add(laoHuoLiangTang);

        MenuComponent yuTouDouFu = new MenuItem("鱼头豆腐", "这是一份鱼头豆腐", 20.0);
        MenuComponent nongJiaCai = new MenuItem("农家菜", "这是一份农家菜", 50.0);
        su.add(yuTouDouFu);
        su.add(nongJiaCai);

        allMenu.add(chuan);
        allMenu.add(yue);
        allMenu.add(su);

        Waitress waitress = new Waitress((Menu) allMenu);
        waitress.showMenu();
    }
}
