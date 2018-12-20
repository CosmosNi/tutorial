package com.cosmos.design.factory;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 20:45
 * @Modified By：
 */
public class People {

    public void paint(String type) {
        ShapeFactory sf = new ShapeFactoryImpl();
        Shape shape = sf.paint(type);
        shape.show();
    }

}
