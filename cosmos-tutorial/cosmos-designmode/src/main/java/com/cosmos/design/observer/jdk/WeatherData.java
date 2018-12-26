package com.cosmos.design.observer.jdk;

import java.util.Observable;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-25 09:32
 * @Modified By：
 */
public class WeatherData extends Observable {
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


    /**
     * 通知观察者
     */
    public void measurementChanged() {
        //将对象标记为已更改
        super.setChanged();
        notifyObservers();
    }

    /**
     * 此处用来测试天气更改
     *
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

    public float getTempearture() {
        return tempearture;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

}
