package com.cc.spring.design.memento;

/**
 * 备忘录模式
 * 主要目的是保存一个对象的某个状态，以便在适当的时候恢复对象
 *
 * @Author: cc
 * @Date: 2019/12/5 16:36
 */
public class Original {

    private String value;

    public Original(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Memento createMemento(){
        return new Memento(this.value);
    }

    public void restoreMemento(Memento memento){
        this.value = memento.getValue();
    }
}
