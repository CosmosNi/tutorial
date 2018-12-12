package com.cosmos.sax;


import com.cosmos.servlet.ServletMapping;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: SAX解析xml处理类
 * @Date: Create in 2018-12-12 13:24
 * @Modified By：
 */
public class XmlParseHandler extends DefaultHandler {

    //存储所有对象
    private List<ServletMapping> servletMappings;
    //记录当前标签
    private String currentTag;
    //记录当前解析到的对象
    private ServletMapping servletMapping;

    /**
     * 文档解析结束后调用
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        System.out.println("文档解析完毕");
    }

    /**
     * 节点解析结束后调用
     *
     * @param uri       : 命名空间的uri
     * @param localName : 标签的名称
     * @param qName     : 带命名空间的标签名称
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        super.endElement(uri, localName, qName);
        //验证尾标签，当以servlet结尾时，于是解析完后，将当前对象放置到容器中，并清除当前对象
        if ("servlet".equals(qName)) {
            servletMappings.add(servletMapping);
            servletMapping = null;
        }
        currentTag = null;
    }

    /**
     * 文档解析开始调用
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("文档解析开始");
        //初始化容器
        servletMappings = new ArrayList<ServletMapping>();
    }

    /**
     * 节点解析开始调用
     *
     * @param uri       : 命名空间的uri
     * @param localName : 标签的名称
     * @param qName     : 带命名空间的标签名称
     */
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //判断头标签
        if ("servlet".equals(qName)) {
            for (int i = 0; i < attributes.getLength(); i++) {
                //初始化当前对象
                servletMapping = new ServletMapping();
                if ("id".equals(attributes.getLocalName(i))) {
                    System.out.println("当前解析：" + attributes.getValue(i));
                    servletMapping.setId(attributes.getValue(i));
                }
            }
        }
        currentTag = qName; // 把当前标签记录下来
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        // 将当前TextNode转换为String
        String value = new String(ch, start, length);
        // 动态赋值
        Class<?> servletMappingClass = ServletMapping.class;
        Method[] methods = servletMappingClass.getMethods();
        Field[] f = servletMappingClass.getDeclaredFields();
        for (Field field : f) {
            if (field.getName().equals(currentTag)) {
                try {
                    Class[] parameterTypes = new Class[1];
                    parameterTypes[0] = field.getType();
                    StringBuffer sb = new StringBuffer();
                    sb.append("set");
                    sb.append(field.getName().substring(0, 1).toUpperCase());
                    sb.append(field.getName().substring(1));

                    Method method = servletMappingClass.getMethod(sb.toString(), parameterTypes);
                    method.invoke(servletMapping, new Object[]{value});
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<ServletMapping> getServletMappings() {
        return servletMappings;
    }


}
