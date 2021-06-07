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
public class DeleteNode implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception{
        zooKeeper = new ZooKeeper("192.168.35.138:2181",5000,new DeleteNode());
        countDownLatch.await();
    }

    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected){
            try {
                deleteNode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteNode() throws Exception{
        /**
         * 判断节点是否存在，存在则删除。
         *
         **/
        Stat exists = zooKeeper.exists("/zk-test0000000000", false);
        System.out.println(exists == null?"节点不存在":"节点存在");
        zooKeeper.delete("/zk-test0000000000",-1);
        Stat exists1 = zooKeeper.exists("/zk-test0000000000", false);
        System.out.println(exists1 == null?"节点不存在":"节点存在");
    }
}
