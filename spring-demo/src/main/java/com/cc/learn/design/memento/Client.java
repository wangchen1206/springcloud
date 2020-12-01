package com.cc.learn.design.memento;

/**
 * @Author: cc
 * @Date: 2019/12/5 16:46
 */
public class Client {
    public static void main(String[] args) {
        //创建原始类
        Original original = new Original("egg");
        //创建备忘录类,同时备份属性
        Storage storage = new Storage(original.createMemento());

        //修改原始状态类
        System.out.println("原始的value: "+original.getValue());
        original.setValue("niu");
        System.out.println("改变后的value: "+original.getValue());

        //恢复原始的value
        original.restoreMemento(storage.getMemento());
        System.out.println("恢复后的value： "+original.getValue());
    }
}
