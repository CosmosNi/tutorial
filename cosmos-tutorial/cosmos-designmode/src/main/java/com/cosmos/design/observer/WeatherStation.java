package com.cosmos.design.observer;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-25 09:23
 * @Modified By：
 */
public class WeatherStation {

    public static void main(String[] args) {
        //初始化一个容器用来存储布告板
        WeatherData weatherData = new WeatherData();
        //将布告板存储到容器中
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(81, 67, 32.4f);
        weatherData.setMeasurements(85, 63, 32.5f);
    }
}
