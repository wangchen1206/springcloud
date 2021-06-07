package com.cc.learn.zookeeper.apache;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2021/05/31
 */
public class GetNodeData implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("192.168.35.138:2181", 5000, new GetNodeData());
        countDownLatch.await();
    }

    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected){
            try {
                getNodeData();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void getNodeData() throws UnsupportedEncodingException, KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData("/lg_persistent", true, null);
        System.out.println(new String(data,"utf-8"));
    }
}
