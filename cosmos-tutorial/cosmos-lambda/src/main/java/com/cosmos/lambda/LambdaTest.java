package com.cosmos.lambda;

import java.util.function.Consumer;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-20 20:20
 * @Modified By：
 */
public class LambdaTest {

    public static void main(String[] args) {
        final Consumer<String> stringConsumer = (String s) -> {
            System.out.println(s);
        };
        stringConsumer.accept("aaa");

        new Thread(()->{
            System.out.println("测试lambda线程");
        }).start();
    }


}
