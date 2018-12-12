package com.cosmos.servlet;

import com.cosmos.http.Request;
import com.cosmos.http.Response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-12 10:54
 * @Modified By：
 */
public class HelloWorldServlet extends Servlet {
    public void doGet(Request request, Response response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(Request request, Response response) throws IOException {
        OutputStream outputStream = response.outputStream;
        String res = response.getHeadType() + "hey，guy！hello world！";
        outputStream.write(res.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
