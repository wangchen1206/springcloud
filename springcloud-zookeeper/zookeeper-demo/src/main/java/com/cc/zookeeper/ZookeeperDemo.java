package com.cc.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2020/08/19
 */
public class ZookeeperDemo implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        //zookeeper 存放数据node路径
        String nodePath = "/username";
        //连接一个zookeeper，并且注册一个默认的监听器
        zooKeeper = new ZooKeeper("localhost:2181", 5000, new ZookeeperDemo());
        //等待zk连接成功通知
        countDownLatch.await();
        //获取path目录节点的数据,并且注册默认的监听器
        System.out.println(new String(zooKeeper.getData(nodePath,true,stat)));


        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){ //zk连接成功通知事件
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()){
                countDownLatch.countDown();
            } else if (watchedEvent.getType() == Event.EventType.NodeDataChanged){ //zk目录节点数据变化通知事件
                try {
                    System.out.println("配置已修改，新值为："+ new String(zooKeeper.getData(watchedEvent.getPath(),true,stat)));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
