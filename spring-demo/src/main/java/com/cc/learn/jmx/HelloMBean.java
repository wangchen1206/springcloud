package com.cc.learn.jmx;

/**
 * 首先定义一个MBean接口，接口的命名规范为以具体的实现类为前缀（这个规范很重要）
 *
 * @author wangchen
 * @createDate 2021/06/04
 */
public interface HelloMBean {
    public String getName();

    public void setName(String name);

    public String getAge();

    public void setAge(String age);

    public void helloWorld();

    public void helloWorld(String str);

    public void getTelephone();
}
