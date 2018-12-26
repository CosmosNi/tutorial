package com.cosmos.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-21 13:09
 * @Modified By：
 */
public class ServerApplication2 {
    public static void main(String[] args) {
        //启动Spring容器
        new ClassPathXmlApplicationContext("classpath:application2.xml");
    }
}
