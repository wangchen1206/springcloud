package com.cc.learn.design.iterator;

/**
 * @Author: cc
 * @Date: 2019/11/27 10:08
 */
public class MyCollection implements Collection {

    public String[] strings = {"A","B","C","D","E"};

    @Override
    public Iterator iterator() {
        return new MyIterator(this);
    }

    @Override
    public Object get(int i) {
        return strings[i];
    }

    @Override
    public int size() {
        return strings.length;
    }
}
