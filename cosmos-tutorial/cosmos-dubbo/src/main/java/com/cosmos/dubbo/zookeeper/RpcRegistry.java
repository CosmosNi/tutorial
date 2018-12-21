package com.cosmos.dubbo.zookeeper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-21 09:04
 * @Modified By：
 */

@Setter
@Getter
@AllArgsConstructor()
@NoArgsConstructor
public class RpcRegistry {

    private String rpcRegistryAddress;
    private ZooKeeper zooKeeper;

    public void createNode(String data) throws IOException, KeeperException, InterruptedException {
        zooKeeper = new ZooKeeper(rpcRegistryAddress, Constant.SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
        if (zooKeeper != null) {
            Stat stat = zooKeeper.exists(Constant.REGISTRY_PATH, false);
            if (stat == null) {
                //如果不存在, 创建一个持久的节点目录
                zooKeeper.create(Constant.REGISTRY_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            //创建一个临时的序列节点,并且保存数据信息
            zooKeeper.create(Constant.DATA_PATH, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        }

    }
    //测试zk
    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        RpcRegistry rpcRegistry = new RpcRegistry();
        rpcRegistry.setRpcRegistryAddress("127.0.0.1:2181");
        rpcRegistry.createNode("testdata");
        //让程序等待输入,程序一直处于运行状态
        System.in.read();
    }
}
