package com.cc.learn.zookeeper.apache;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2021/05/31
 */
public class CreateNode implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("192.168.35.138:2181", 5000, new CreateNode());
        countDownLatch.await();
        System.out.println(zooKeeper.getState());
    }


    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
//            countDownLatch.countDown();
        }
        //创建节点
        try {
            createNodeSync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNodeSync() throws Exception {
        String node1 = zooKeeper.create("/lg_persistent/lg_children", "子节点内容".getBytes("utf-8"),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        String node1 = zooKeeper.create("/lg_persistent", "持久节点内容".getBytes("utf-8"),
//                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        String node2 = zooKeeper.create("/lg_persistent_sequential", "持久性顺序节点内容".getBytes("utf-8"),
//                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
//        String node3 = zooKeeper.create("/lg_ephemeral", "临时节点内容".getBytes("utf-8"),
//                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//        System.out.println("创建的持久节点是： " + node1);
//        System.out.println("创建的持久顺序节点是： " + node2);
//        System.out.println("创建的临时节点是： " + node3);
    }
}
