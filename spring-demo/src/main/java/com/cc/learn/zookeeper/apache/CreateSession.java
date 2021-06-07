package com.cc.learn.zookeeper.apache;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2021/05/31
 */
public class CreateSession implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper("192.168.35.138:2181", 5000, new CreateSession());
        System.out.println(zooKeeper.getState());
        countDownLatch.await();
        System.out.println(zooKeeper.getState()+"  ===create session successfully");
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == org.apache.zookeeper.Watcher.Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
            System.out.println("创建连接完毕");
        }
    }
}
