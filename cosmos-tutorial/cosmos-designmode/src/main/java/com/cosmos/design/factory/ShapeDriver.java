package com.cosmos.design.factory;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 工厂方法模式定义一个创建对象的接口，但由子类决定要实例化的类是哪一个。工厂方法让泪的实例化
 * 推迟到子类
 * @Date: Create in 2018-12-14 20:41
 * @Modified By：
 */
public class ShapeDriver {

    public static void main(String[] args) {
        new People().paint("circle");
        new People().paint("square");
        new People().paint("");
    }
}
