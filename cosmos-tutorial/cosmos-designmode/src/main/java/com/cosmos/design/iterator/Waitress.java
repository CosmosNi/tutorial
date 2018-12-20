package com.cosmos.design.iterator;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 09:42
 * @Modified By：
 */
public class Waitress {

    Menu allMenu;

    public Waitress(Menu allMenu) {
        this.allMenu = allMenu;
    }

    public void showMenu() {
        allMenu.print();
    }
}
