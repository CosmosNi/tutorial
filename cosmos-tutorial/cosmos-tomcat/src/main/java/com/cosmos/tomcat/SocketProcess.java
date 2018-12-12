package com.cosmos.tomcat;

import com.cosmos.http.Request;
import com.cosmos.http.Response;
import com.cosmos.servlet.Servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-12 10:56
 * @Modified By：
 */
public class SocketProcess extends Thread {

    protected Socket socket;

    public SocketProcess(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //
            Request request = new Request(socket.getInputStream());
            Response response = new Response(socket.getOutputStream());
            //获取对象，用来处理请求
            Servlet servlet = CosmosTomcat.servletMappingMap.get(request.getUrl());

            if (servlet != null) {
                servlet.service(request, response);
            } else {
                String res = response.getHeadType() + "您访问的链接不存在！";
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(res.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
