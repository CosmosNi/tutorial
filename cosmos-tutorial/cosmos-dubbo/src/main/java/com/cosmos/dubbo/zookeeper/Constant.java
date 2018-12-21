package com.cosmos.dubbo.zookeeper;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-21 09:00
 * @Modified By：
 */
public interface Constant {
    /**
     * 定义客户端连接session会话超时时间,单位为毫秒,该值的设置和zkServer设置的心跳时间有关系
     */
    int SESSION_TIMEOUT = 4000;
    /**
     * 定义用于保存rpc通信服务端的地址信息的目录
     */
    String REGISTRY_PATH = "/rpc";
    /**
     * 定义数据存放的具体目录
     */
    String DATA_PATH = REGISTRY_PATH + "/data";
}
