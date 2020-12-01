package com.cc.learn.design.observer;

/**
 * 观察者模式
 * MySubject类就是我们的主对象，Observer1和Observer2是依赖于MySubject的对象，
 * 当MySubject变化时，Observer1和Observer2必然变化。AbstractSubject类中定义着需要监控的对象列表，可以对其进行修改：增加或删除被监控对象，
 * 且当MySubject变化时，负责通知在列表内存在的对象
 *
 * @Author: cc
 * @Date: 2019/11/26 15:59
 */
public interface Observer {
    public void update();
}
