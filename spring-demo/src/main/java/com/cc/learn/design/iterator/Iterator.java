package com.cc.learn.design.iterator;

/**
 * 迭代模式
 * 顺序访问聚集中的对象，一般来说，集合中非常常见，如果对集合类比较熟悉的话，理解本模式会十分轻松。
 * 这句话包含两层意思：一是需要遍历的对象，即聚集对象，二是迭代器对象，用于对聚集对象进行遍历访问
 *
 * @Author: cc
 * @Date: 2019/11/27 10:00
 */
public interface Iterator {

    /**
     * 前移
     * @return
     */
    public Object previous();

    /**
     * 下一个
     * @return
     */
    public Object next();

    /**
     * 是否有下一个
     * @return
     */
    public boolean hasNext();

    /**
     * 第一个
     * @return
     */
    public Object  first();
}
