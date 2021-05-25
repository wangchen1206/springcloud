package com.cc.learn.design.singleton;

/**
 * 双重校验锁
 *
 * @author wangchen
 * @createDate 2021/04/12
 */
public class Singleton3 {
    public static volatile Singleton3 singleton3 ;

    public static Singleton3 getInstance(){
        if (singleton3 == null){
            synchronized (Singleton3.class){
                if (singleton3 == null){
                    singleton3 = new Singleton3();
                }
            }
        }
        return singleton3;
    }

    private void sing(){
        System.out.println(111);
    }

    public static void main(String[] args) {
        Singleton3 instance = Singleton3.getInstance();
        instance.sing();
    }
}
