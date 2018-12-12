package com.cosmos.servlet;

import com.cosmos.http.Request;
import com.cosmos.http.Response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-12 11:25
 * @Modified By：
 */
public class IndexServlet extends Servlet {
    public void doGet(Request request, Response response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(Request request, Response response) throws IOException {
        OutputStream outputStream = response.outputStream;

        String res = response.getHeadType() + "欢迎来到我的首页!";

        outputStream.write(res.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
