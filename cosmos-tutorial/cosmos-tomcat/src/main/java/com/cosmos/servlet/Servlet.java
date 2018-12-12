package com.cosmos.servlet;

import com.cosmos.http.Request;
import com.cosmos.http.Response;

import java.io.IOException;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-12 10:51
 * @Modified By：
 */
public abstract class Servlet {

    public void service(Request request, Response response) throws IOException {
        //判断请求方法
        if ("get".equals(request.getMethod())) {
            this.doGet(request, response);
        } else {
            this.doPost(request, response);
        }
    }

    ;

    public abstract void doGet(Request request, Response response) throws IOException;

    public abstract void doPost(Request request, Response response) throws IOException;
}
