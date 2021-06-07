package com.cc.learn.zookeeper.apache;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2021/05/31
 */
public class UpdateNode implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper ;

    public static void main(String[] args) throws Exception{
        zooKeeper = new ZooKeeper("192.168.35.138:2181",5000,new UpdateNode());
        countDownLatch.await();
    }

    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected){
            try {
                updateNodeSync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateNodeSync() throws Exception{
        byte[] data = zooKeeper.getData("/lg_persistent", false, null);
        System.out.println("修改前值： "+new String(data,"utf-8"));
        //修改
        Stat stat = zooKeeper.setData("/lg_persistent", "客户端修改节点内容".getBytes("utf-8"), -1);
        byte[] data1 = zooKeeper.getData("/lg_persistent", false, null);
        System.out.println("修改后内容： "+new String(data1,"utf-8"));
    }
}
