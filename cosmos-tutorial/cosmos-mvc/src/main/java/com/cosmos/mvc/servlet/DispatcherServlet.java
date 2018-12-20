package com.cosmos.mvc.servlet;

import com.cosmos.mvc.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-19 10:11
 * @Modified By：
 */
public class DispatcherServlet extends HttpServlet {

    private Logger log = Logger.getLogger("init");

    /**
     * 读取外部配置文件
     */
    private Properties properties = new Properties();

    /**
     * 存储所有的bean
     */
    private List<String> beanNames = new ArrayList<>();

    /**
     * 缓存 key/value: 类注解参数/类实例对象，存储controller和service实例
     */
    private Map<String, Object> instanceMaps = new HashMap<>();
    /**
     * 维护存储Controller的map
     */
    private Map<String, Object> controllerMap = new HashMap<>();
    /**
     * 请求处理的方法
     */
    private Map<String, Method> handlerMapping = new HashMap<>();
    /**
     * 部署到tomcat的路径
     */
    private String tomcatUrl = "/cosmos";


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        log.info("初始化DispatcherServlet");
        //加载配置
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //扫描所有包
        doScanner(properties.getProperty("ScanPackage"));
        //bean托管
        doInstance();
        //bean的注入
        doAutowire();
        //将url与类，方法做绑定
        initHandleMapping();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //dGet()方法的协议会报错
        //super.doGet(req, resp);
        //解决浏览器乱码问题
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        doDispatcher(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        doDispatcher(req, resp);
        resp.setHeader("Content-type", "text/html;charset=UTF-8");

    }

    /**
     * 处理请求
     */
    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (handlerMapping.isEmpty()) {
            log.info("没有可映射的url");
            resp.getWriter().write("目前没有可用链接");
        }
        //获取请求
        //String url = req.getRequestURI();
        //返回站点的根目录(http://localhost:8080/testweb/hello),返回/testweb
        //String contextPath = req.getContextPath();
        String uri = req.getRequestURI();
        log.info("请求：" + uri);
        if (!handlerMapping.containsKey(uri)) {
            resp.getWriter().write("404 NOT FOUND!");
        }
        Method method = handlerMapping.get(uri);
        //获取方法的参数列表
        Parameter[] parameterTypes = method.getParameters();
        //获取请求的参数
        Map<String, String[]> parameterMap = req.getParameterMap();
        //将参数保存到数组中
        Object[] paramValues = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            //处理HttpServletRequest，HttpServletResponse
            if (ServletRequest.class.isAssignableFrom(parameterTypes[i].getType())) {
                paramValues[i] = req;
                continue;
            }
            if (ServletResponse.class.isAssignableFrom(parameterTypes[i].getType())) {
                paramValues[i] = resp;
                continue;
            }
            String methodParamsName = parameterTypes[i].getName();
            if (parameterTypes[i].isAnnotationPresent(RequestParam.class)) {
                methodParamsName = parameterTypes[i].getAnnotation(RequestParam.class).value();
            }
            //获取请求的值
            String paramValue = req.getParameter(methodParamsName);
            paramValues[i] = paramValue;
            if (paramValue != null) {
                if (Integer.class.isAssignableFrom(parameterTypes[i].getType())) {
                    paramValues[i] = Integer.parseInt(paramValue);
                } else if (Float.class.isAssignableFrom(parameterTypes[i].getType())) {
                    paramValues[i] = Float.parseFloat(paramValue);
                } else if (Double.class.isAssignableFrom(parameterTypes[i].getType())) {
                    paramValues[i] = Double.parseDouble(paramValue);
                }
            }
        }
        try {
            Object message = null;
            if (paramValues.length == 0) {
                message = method.invoke(controllerMap.get(uri));
            } else {
                message = method.invoke(controllerMap.get(uri), paramValues);
            }
            if (message == null) {
                resp.getWriter().write("Success!");
            } else {
                resp.getWriter().write(message.toString());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        resp.getWriter().write("500!error");
    }

    /**
     * 初始化配置
     */
    private void doLoadConfig(String location) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(location);
        try {
            log.info("读取配置文件" + location);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 扫描包路径下所有的class
     *
     * @param basePackage
     */
    private void doScanner(String basePackage) {
        log.info("扫描包路径");
        //将包名转换为路径
        URL url = this.getClass().getClassLoader().getResource("/" + basePackage.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                //递归读取文件
                doScanner(basePackage + "." + file.getName());
            } else {
                String beanName = basePackage + "." + file.getName().replace(".class", "");
                beanNames.add(beanName);
            }
        }
    }

    /**
     * 将所有的bean交于spring管理
     */
    private void doInstance() {
        log.info("将所有的bean交于spring管理");
        if (beanNames.isEmpty()) {
            return;
        }
        try {
            for (String beanName : beanNames) {
                Class<?> clazz = Class.forName(beanName);
                //如果这个类是个controller，则需要实例化并放入缓存
                if (clazz.isAnnotationPresent(Controller.class)) {
                    Object controllerInstance = clazz.newInstance();
                    //获取Controller的别名
                    String insMapKey = clazz.getAnnotation(Controller.class).value();
                    if ("".equals(insMapKey)) {
                        insMapKey = toLowerFirstWord(clazz.getSimpleName());
                    }
                    instanceMaps.put(insMapKey, controllerInstance);
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    Object serviceImplInstance = clazz.newInstance();
                    String insMapKey = clazz.getAnnotation(Service.class).value();
                    if ("".equals(insMapKey)) {
                        insMapKey = toLowerFirstWord(clazz.getSimpleName());
                    }
                    instanceMaps.put(insMapKey, serviceImplInstance);

                    //将接口与实现类绑定
                    Class[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        instanceMaps.put(i.getName(), serviceImplInstance);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 依赖注入
     */
    private void doAutowire() {
        log.info("依赖注入");
        if (instanceMaps.isEmpty()) {
            return;
        }
        //
        for (Map.Entry<String, Object> entry : instanceMaps.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Autowire.class)) {
                    continue;
                }
                Autowire autowire = field.getAnnotation(Autowire.class);
                String beanName = autowire.value().trim();
                if ("".equals(beanName)) {
                    //如果没定义beanname，就使用类名
                    beanName = field.getType().getName();
                }
                log.info("类名：" + beanName);
                //如果字段是私有的,那么必须要对这个字段设置
                field.setAccessible(true);
                try {
                    //将bean注入到类中
                    field.set(entry.getValue(), instanceMaps.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 请求映射
     */
    private void initHandleMapping() {
        log.info("请求映射");
        if (instanceMaps.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : instanceMaps.entrySet()) {
            Class<? extends Object> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(Controller.class)) {
                continue;
            }
            String baseuURL = tomcatUrl;
            //拼接类名上RequestMapping的url
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                String topUrl = clazz.getAnnotation(RequestMapping.class).value();
                if (topUrl.startsWith("/")) {
                    baseuURL = topUrl;
                } else {
                    baseuURL = "/" + topUrl;
                }

            }
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                //如果方法没有RequestMapping注解，则跳过
                String url = baseuURL;
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }
                String methodUrl = method.getAnnotation(RequestMapping.class).value();
                //字符拼接
                if (methodUrl.startsWith("/")) {
                    url = url + methodUrl;
                } else {
                    url = url + "/" + methodUrl;
                }
                //放入缓存
                handlerMapping.put(url, method);
                controllerMap.put(url, entry.getValue());
            }
        }
    }

    /**
     * Description:  将字符串中的首字母小写
     */
    private String toLowerFirstWord(String name) {

        char[] charArray = name.toCharArray();
        charArray[0] += 32;
        return String.valueOf(charArray);
    }

}
