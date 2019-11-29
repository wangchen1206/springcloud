package com.cc.spring.design.iterator;

/**
 * @Author: cc
 * @Date: 2019/11/27 9:58
 */
public interface Collection {

    public Iterator iterator();

    /**
     * 取第i个元素
     * @param i
     * @return
     */
    public Object get(int i);

    /**
     * 集合大小
     * @return
     */
    public int size();
}
