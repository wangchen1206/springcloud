package com.springcloud.stream;

/**
 * @Author: cc
 * @Date: 2019/10/29 16:23
 */
public class Foo {
    private int id;
    private String bar;

    public Foo() {
    }

    public Foo(int id, String bar) {
        this.id = id;
        this.bar = bar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "id=" + id +
                ", bar='" + bar + '\'' +
                '}';
    }
}
