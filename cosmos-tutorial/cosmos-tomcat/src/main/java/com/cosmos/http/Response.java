package com.cosmos.http;

import java.io.OutputStream;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-12 10:46
 * @Modified By：
 */
public class Response {

    public OutputStream outputStream;

    private String header = "HTTP/1.1 200";

    private String contentType = "text/html;charset=utf-8";

    public static final String responseHeader = "HTTP/1.1 200\r\n"
            + "Content-Type: text/html;charset=utf-8\r\n"
            + "\r\n";

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public final String getHeadType(){
        StringBuilder sb = new StringBuilder();
        sb.append(header).append("\r\n");
        sb.append("Content-Type").append(":");
        sb.append(contentType).append("\r\n").append("\r\n");
       return sb.toString();
    }
}
