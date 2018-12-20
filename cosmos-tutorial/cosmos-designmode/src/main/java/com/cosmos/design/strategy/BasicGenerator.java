package com.cosmos.design.strategy;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-19 21:01
 * @Modified By：
 */
public class BasicGenerator<T> {
    private Class<T> type;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    public T next() throws IllegalAccessException, InstantiationException {
        return type.newInstance();
    }

    public static <T> BasicGenerator<T> create(Class<T> type) {
        return new BasicGenerator<T>(type);
    }
}
