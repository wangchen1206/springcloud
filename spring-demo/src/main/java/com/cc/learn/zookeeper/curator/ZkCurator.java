package com.cc.learn.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2021/05/31
 */
public class ZkCurator {

    public static void main(String[] args) throws Exception {
//        createSession();
        createNode();
//        deleteNode();
//        getNodeData();
//        updateNodeData();
    }


    private static void createSession() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client =
                CuratorFrameworkFactory.newClient("192.168.35.138:2181", 5000, 3000, retryPolicy);
        client.start();

        System.out.println("Zookeeper session1 established. ");
        CuratorFramework client1 = CuratorFrameworkFactory.builder()
                .connectString("192.168.35.138:2181") //server地址
                .sessionTimeoutMs(5000) // 会话超时时间
                .connectionTimeoutMs(3000) // 连接超时时间
                .retryPolicy(retryPolicy) // 重试策略
                .namespace("base") // 独立命名空间 /base
                .build(); //
        client1.start();
        System.out.println("Zookeeper session2 established. ");
    }

    private static void createNode() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.35.138:2181") //server地址
                .sessionTimeoutMs(5000) // 会话超时时间
                .connectionTimeoutMs(3000) // 连接超时时间
                .retryPolicy(new ExponentialBackoffRetry(1000, 5)) //重试策略
                .build(); //
        client.start();
        System.out.println("Zookeeper session established. ");

        String path = "/lg-test/c1";
        client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, "init".getBytes());
        Thread.sleep(50000);
        System.out.println("success create znode" + path);
    }

    private static void deleteNode() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.35.138:2181") //server地址
                .sessionTimeoutMs(5000) // 会话超时时间
                .connectionTimeoutMs(3000) // 连接超时时间
                .retryPolicy(new ExponentialBackoffRetry(1000, 5)) //重试策略
                .build(); //
        client.start();
        System.out.println("Zookeeper session established. ");
        String path = "/lg-curator";
        client.delete().deletingChildrenIfNeeded().withVersion(-1).forPath(path);
        System.out.println("success delete node "+path);

    }

    private static void getNodeData() throws Exception{
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.35.138:2181") //server地址
                .sessionTimeoutMs(5000) // 会话超时时间
                .connectionTimeoutMs(3000) // 连接超时时间
                .retryPolicy(new ExponentialBackoffRetry(1000, 5)) //重试策略
                .build(); //
        client.start();
        System.out.println("Zookeeper session established. ");

        //创建节点
        String path = "/lg-curator/c1";
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path,"init".getBytes());
        System.out.println("success create znode "+ path);

        //获取节点内容
        Stat stat = new Stat();
        byte[] bytes = client.getData().storingStatIn(stat).forPath(path);
        System.out.println(path+"节点内容： "+new String(bytes));
    }

    private static void updateNodeData() throws Exception{
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.35.138:2181") //server地址
                .sessionTimeoutMs(5000) // 会话超时时间
                .connectionTimeoutMs(3000) // 连接超时时间
                .retryPolicy(new ExponentialBackoffRetry(1000, 5)) //重试策略
                .build(); //
        client.start();
        System.out.println("Zookeeper session established. ");

        //获取节点内容
        String path = "/lg-curator/c1";
        Stat stat = new Stat();
        byte[] bytes = client.getData().storingStatIn(stat).forPath(path);
        System.out.println(new String(bytes));

        //更新节点内容
        int version = client.setData().withVersion(stat.getVersion()).forPath(path, "update".getBytes()).getVersion();
        System.out.println(version);

    }
}
