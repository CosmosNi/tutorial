package com.cosmos.design.proxy;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-21 14:53
 * @Modified By：
 */
public class Student implements People {


    @Override
    public String doOperation(String name, String action) {
        return name + "正在" + action;
    }

}
