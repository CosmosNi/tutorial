package com.cosmos.design.observer;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-25 09:04
 * @Modified By：
 */
public interface Observer {
    /**
     * 更新布告板功能
     * @param temp
     * @param humidity
     * @param pressure
     */
    public void update(float temp, float humidity, float pressure);
}
