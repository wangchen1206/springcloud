package com.cc.learn.design.strategy;

/**
 * 策略模式
 * 策略模式定义了一系列算法，并将每个算法封装起来，使他们可以相互替换，且算法的变化不会影响到使用算法的客户。
 * 需要设计一个接口，为一系列实现类提供统一的方法，多个实现类实现该接口，设计一个抽象类（可有可无，属于辅助类），提供辅助函数
 * 策略模式多用在算法决策系统中，外部用户只需要决定用哪个算法即可。
 *
 * @Author: cc
 * @Date: 2019/11/26 14:02
 */
public interface ICalculator {
    public int calculate(String exp);
}
