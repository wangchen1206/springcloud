package com.cc.spring.design.iterator;

/**
 * @Author: cc
 * @Date: 2019/11/27 10:25
 */
public class Client {
    public static void main(String[] args) {
        Collection collection = new MyCollection();
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
