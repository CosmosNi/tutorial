package com.cosmos;

import com.cosmos.tomcat.CosmosTomcat;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: 简单的Tomcat实现
 *               Tomcat服务器本质上一个Server程序，底层的实现使用socket进行连接
 * @Date: Create in 2018-12-12 14:05
 * @Modified By：
 */
public class Application {
    public static void main(String[] args) {
        CosmosTomcat tomcat = new CosmosTomcat();
        tomcat.start(8099);
    }
}
