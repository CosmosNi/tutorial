package com.cosmos.design.proxy;

import java.lang.reflect.Proxy;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-21 15:01
 * @Modified By：
 */
public class DynamicProxyDriver {

    static People getPersonProxy(People people) {
        return (People) Proxy.newProxyInstance(people.getClass().getClassLoader(), people.getClass().getInterfaces(), new DynamicProxy(people));
    }

    static People getCglibProxy(People people) {
        return (People) new CglibProxy().getInstance(people);
    }

    public static void main(String[] args) {
        People people = new Student();
        People proxyPeople = getPersonProxy(people);
        proxyPeople.doOperation("张三", "提交作业！");

        People cglibPeople = getCglibProxy(people);
        cglibPeople.doOperation("张三", "提交作业！");
    }
}
