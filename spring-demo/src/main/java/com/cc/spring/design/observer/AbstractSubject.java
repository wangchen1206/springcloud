package com.cc.spring.design.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Author: cc
 * @Date: 2019/11/26 16:12
 */
public abstract class AbstractSubject implements Subject{

    private Vector<Observer> vector = new Vector<>();

    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        Enumeration<Observer> enumeration = vector.elements();
        while (enumeration.hasMoreElements()){
            enumeration.nextElement().update();
        }
    }
}
