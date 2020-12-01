package com.cc.learn.design.factory;

/**
 * 抽象工厂模式
 * 关注创建单个产品
 *
 * @Author: cc
 * @Date: 2019/11/22 15:05
 */
public interface Producer {
    public Sender produce();
}
