package com.cc.learn.reflet;

/**
 * @Author: cc
 * @Date: 2019/11/13 14:18
 */
public interface Fruit {

    public abstract void eat();
}

class Apple implements Fruit{

    @Override
    public void eat() {
        System.out.println("eat Apple");
    }
}

class Orange implements Fruit{

    @Override
    public void eat() {
        System.out.println("eat Orange");
    }
}

class Banana implements Fruit{

    @Override
    public void eat() {
        System.out.println("eat Banana");
    }
}

class Factory{
    public static Fruit getInstance(String className){
        Fruit fruit = null;
        try {
            fruit = (Fruit)Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fruit;
    }
}

class hello{
    public static void main(String[] args) {
        Fruit f = Factory.getInstance("com.cc.spring.reflet.Banana");
        if (f!=null) f.eat();
    }
}