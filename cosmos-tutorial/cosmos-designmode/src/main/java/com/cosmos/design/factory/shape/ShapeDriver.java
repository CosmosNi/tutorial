package com.cosmos.design.factory.shape;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
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
