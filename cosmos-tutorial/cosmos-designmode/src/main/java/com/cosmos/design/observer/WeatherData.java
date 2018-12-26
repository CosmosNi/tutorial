package com.cosmos.design.observer;

import java.util.ArrayList;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-25 09:07
 * @Modified By：
 */
public class WeatherData implements Subject {
    /**
     * 布告板集合
     */
    private ArrayList<Observer> observerList = new ArrayList<>();
    /**
     * 温度
     */
    private float tempearture;
    /**
     * 湿度
     */
    private float humidity;
    /**
     * 压强
     */
    private float pressure;

    @Override
    public void refisterObserver(Observer o) {
        if (!observerList.contains(o)) {
            observerList.add(o);
        } else {
            System.out.println("此布告板以注册！");
        }
    }

    @Override
    public void removeObervers(Observer o) {
        if (observerList.contains(o)) {
            observerList.remove(o);
        } else {
            System.out.println("无此布告板");
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update(tempearture, humidity, pressure);
        }
    }

    /**
     * 通知观察者
     */
    public void measurementChanged() {
        notifyObservers();
    }

    /**
     * 此处用来测试天气更改
     * @param tempearture
     * @param humidity
     * @param pressure
     */
    public void setMeasurements(float tempearture, float humidity, float pressure) {
        this.tempearture = tempearture;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }

}
