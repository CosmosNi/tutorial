package com.cosmos.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @Author: Cosmos
 * @program: selenium
 * @Description: web端的自动化测试
 * @Date: Create in 2018-12-03 16:31
 * @Modified By：
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        String basePath = Application.class.getResource("/").getPath();
        System.setProperty("webdriver.chrome.driver", basePath + "chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //get 到百度首页
        driver.get("https://www.baidu.com/");
        System.out.printf("now accesss %s \n", driver.getCurrentUrl());
        Thread.sleep(2000);

        driver.findElement(By.linkText("贴吧")).click();
        Thread.sleep(2000);


        //执行浏览器后退
        driver.navigate().back();
        System.out.printf("back to %s \n", driver.getCurrentUrl());
        Thread.sleep(2000);

        //执行浏览器前面
        driver.navigate().forward();
        System.out.printf("forward to %s \n", driver.getCurrentUrl());
        Thread.sleep(2000);


        driver.findElement(By.linkText("新闻")).click();
        Thread.sleep(2000);

        WebElement input = driver.findElement(By.id("ww"));

        //输入框输入内容
        input.sendKeys("peterr");
        Thread.sleep(2000);

        //删除多输入的一个 m
        input.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(2000);

        //输入空格键+“教程”
        input.sendKeys(Keys.SPACE);
        input.sendKeys("是一头猪吗");
        Thread.sleep(2000);

        //ctrl+a 全选输入框内容
        input.sendKeys(Keys.CONTROL, "a");
        Thread.sleep(2000);

        //ctrl+x 剪切输入框内容
        input.sendKeys(Keys.CONTROL, "x");
        Thread.sleep(2000);

        //ctrl+v 粘贴内容到输入框
        input.sendKeys(Keys.CONTROL, "v");
        Thread.sleep(2000);

        //通过回车键盘来代替点击操作
        input.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        driver.findElement(By.linkText("网页")).click();
        Thread.sleep(6000);

        driver.quit();

    }
}
