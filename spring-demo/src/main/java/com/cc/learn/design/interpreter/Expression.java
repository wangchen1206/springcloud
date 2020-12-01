package com.cc.learn.design.interpreter;

/**
 * 解释器模式
 * 解释器模式用来做各种各样的解释器，如正则表达式等的解释器等等
 *
 * @Author: cc
 * @Date: 2019/12/6 14:23
 */
public interface Expression {
    public int interpreter(Context context);
}
