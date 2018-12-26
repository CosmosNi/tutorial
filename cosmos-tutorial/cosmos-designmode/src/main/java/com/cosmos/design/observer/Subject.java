package com.cosmos.design.observer;


/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-25 09:01
 * @Modified By：
 */
public interface Subject {
    /**
     * 注册需要通知的观察者
     * @param o
     */
    public void refisterObserver(Observer o);

    /**
     * 移除观察者
     * @param o
     */
    public void removeObervers(Observer o);

    /**
     * 通知所有注册的观察者
     */
    public void notifyObservers();
}
