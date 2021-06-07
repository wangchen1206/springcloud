package com.cc.learn.jmx;

/**
 * 该类名称必须与实现的接口的前缀保持一致（即MBean前面的名称）
 *
 * @author wangchen
 * @createDate 2021/06/04
 */
public class Hello implements HelloMBean {
    private String name;
    private String age;


    public String getName() {
        System.out.println("get name 123");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        System.out.println("get age 123");
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void helloWorld() {
        System.out.println("hello world");
    }

    public void helloWorld(String str) {
        System.out.println("hello world: "+str);
    }

    public void getTelephone() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Hello{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
