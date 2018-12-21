package com.cosmos.dubbo.zookeeper;

import lombok.Getter;
import lombok.Setter;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-21 09:12
 * @Modified By：
 */
@Getter
@Setter
public class RpcDiscover {
    /**
     * 服务端地址 zkServer的地址
     */
    private String registryAddress;
    //获取到的所有提供服务的服务器列表
    private volatile List<String> dataList = new ArrayList<>();

    private ZooKeeper zooKeeper = null;

    /**
     * 初始化zkClient客户端
     */
    public RpcDiscover(String registryAddress) throws KeeperException, InterruptedException, IOException {
        this.registryAddress = registryAddress;
        zooKeeper = new ZooKeeper(registryAddress, Constant.SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                    //监听zkServer的服务器列表变化
                    try {
                        checkNode();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //获取节点相关数据
        checkNode();
    }

    public String discover() {

        int size = dataList.size();
        if (size > 0) {
            int index = new Random().nextInt(size);
            return dataList.get(index);
        }
        throw new RuntimeException("没有找到对应的服务器");
    }

    private void checkNode() throws KeeperException, InterruptedException {
        List<String> nodeList = zooKeeper.getChildren(Constant.REGISTRY_PATH, true);
        List<String> dataList = new ArrayList<>();
        for (String node : nodeList) {
            System.out.println("zk服务列表");
            byte[] bytes = zooKeeper.getData(Constant.REGISTRY_PATH + "/" + node, false, null);
            dataList.add(new String(bytes));
        }
        this.dataList = dataList;
    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        System.out.println(new RpcDiscover("127.0.0.1:2181").discover());
        System.in.read();
    }
}
