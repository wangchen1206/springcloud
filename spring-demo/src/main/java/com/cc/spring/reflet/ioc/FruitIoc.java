package com.cc.spring.reflet.ioc;

import com.cc.spring.reflet.Fruit;

import java.io.*;
import java.util.Properties;

/**
 * @Author: cc
 * @Date: 2019/11/13 14:18
 */
public interface FruitIoc {

    public abstract void eat();
}

class Apple implements Fruit {

    @Override
    public void eat() {
        System.out.println("eat Apple");
    }
}

class Orange implements Fruit {

    @Override
    public void eat() {
        System.out.println("eat Orange");
    }
}

class Banana implements Fruit {

    @Override
    public void eat() {
        System.out.println("eat Banana");
    }
}

class Init {
    public static Properties getPro(String propertiesPath) throws IOException {
        Properties properties = new Properties();
        File file = new File(propertiesPath);
        if (file.exists()) {
            properties.load(new FileInputStream(file));
        } else {
            System.out.println("file is not exist");
            properties.setProperty("apple","com.cc.spring.reflet.ioc.Apple");
            properties.setProperty("orange","com.cc.spring.reflet.ioc.Orange");
            properties.setProperty("banana","com.cc.spring.reflet.ioc.Banana");
            properties.store(new FileOutputStream(file),"Fruit Class");
        }
        return properties;
    }
}

class Factory {
    public static Fruit getInstance(String className) {
        Fruit fruit = null;
        try {
            fruit = (Fruit) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fruit;
    }
}

class hello {
    public static void main(String[] args) throws IOException {
        Properties pro = Init.getPro("fruit.properties");
        Fruit f = Factory.getInstance(pro.getProperty("apple"));
        if (f != null) f.eat();
    }
}