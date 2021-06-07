package com.cc.learn.zookeeper.zkcli;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2021/05/31
 */
public class ZkClientLearn {
    private static ZkClient zkClient;
    public static void main(String[] args) throws Exception {
        zkClient = new ZkClient("192.168.35.138:2181");
//        createSession();
//        createNode();
//        deleteNode();
//        getChildren();
        getData();
    }


    private static void createSession(){
        System.out.println("create zk session");
    }

    private static void createNode(){
        zkClient.createPersistent("/lg_zk/child2",true);
    }

    private static void deleteNode(){
        String path = "/lg_zk/child1";
        zkClient.deleteRecursive(path);
    }

    private static void getChildren() throws Exception{
        String path = "/lg_zk";
        List<String> children = zkClient.getChildren(path);
        System.out.println(children);
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println(s+"'s child changed,currentChilds : "+list);
            }
        });

        zkClient.createPersistent("/lg_zk/child3");
        Thread.sleep(1000);
        zkClient.delete("/lg_zk/child3");
        Thread.sleep(1000);
        zkClient.delete("/lg_zk/child2");
        Thread.sleep(1000);
        zkClient.delete(path);
        Thread.sleep(Integer.MAX_VALUE);
    }

    private static void getData() throws Exception{
        String path = "/test";
        boolean exists = zkClient.exists(path);
        if (!exists){
            zkClient.createEphemeral(path,"123");
        }

        //注册监听
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println(s+" 该节点内容被更新，更新后的内容是： "+o);
            }

            public void handleDataDeleted(String s) throws Exception {
                System.out.println(s+" 该节点已被删除");
            }
        });

        //获取节点内容
        Object o = zkClient.readData(path);
        System.out.println(o);

        //更新节点内容
        zkClient.writeData(path,"4567");
        Thread.sleep(20000);

        //删除节点
        zkClient.delete(path);
        Thread.sleep(1000);
    }
}
