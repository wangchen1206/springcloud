package com.cc.learn.concurrent;

import java.util.HashMap;

public class HashMapDemo {
 
    public static void main(String[] args) {
        HashMap map = new HashMap<HashMapDemo,Integer>(1);
        for (int i = 0; i < 1000; i++) {
            HashMapDemo hashMapDemo1 = new HashMapDemo();
            map.put(hashMapDemo1, null);
        }
        System.out.println("运行结束");
    }
 
    @Override
    public int hashCode() {
        return 1;
    }
}