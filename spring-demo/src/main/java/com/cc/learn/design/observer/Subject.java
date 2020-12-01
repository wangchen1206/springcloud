package com.cc.learn.design.observer;

/**
 * @Author: cc
 * @Date: 2019/11/26 16:04
 */
public interface Subject {
    /**
     * 增加观察者
     * @param observer
     */
    public void add(Observer observer);

    /**
     * 删除观察者
     * @param observer
     */
    public void del(Observer observer);

    /**
     * 通知所有观察者
     */
    public void notifyAllObservers();

    /**
     * 自身的操作
     */
    public void operation();
}
