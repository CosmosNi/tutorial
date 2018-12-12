package com.cosmos.tomcat;

import com.cosmos.sax.XmlParseUtils;
import com.cosmos.servlet.Servlet;
import com.cosmos.servlet.ServletMapping;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-12 10:59
 * @Modified By：
 */
public class CosmosTomcat {

    public static final HashMap<String, Servlet> servletMappingMap = new HashMap<String, Servlet>();

    /**
     * 初始化，解析xml，以url作为key，类作为value，用作分发请求
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        XmlParseUtils xmlParseUtils = new XmlParseUtils();
        List<ServletMapping> servletMappings = xmlParseUtils.parse();
        for (ServletMapping servletMapping : servletMappings) {
            servletMappingMap.put(servletMapping.getUrl(), (Servlet) Class.forName(servletMapping.getClassName()).newInstance());
        }
    }


    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            this.init();
            System.out.println("Tomcat 服务已启动，地址：localhost ,端口：" + port);
            while (true) {
                //重复接收请求，并处理
                Socket socket = serverSocket.accept();
                Thread thread = new SocketProcess(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
