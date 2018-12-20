package com.cosmos.design.factory;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-14 20:43
 * @Modified By：
 */
public class ShapeFactoryImpl implements ShapeFactory {
    public Shape paint(String type) {
        if (type.equals("circle")) {
            return new Circle();
        }
        if (type.equals("square")) {
            return new Square();
        }
        return new DefalutShape();
    }
}
