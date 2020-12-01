package com.cc.learn.design.command;

/**
 * 命令模式
 * 命令模式的目的就是达到命令的发出者和执行者之间解耦，实现请求和执行分开
 *
 * @Author: cc
 * @Date: 2019/12/5 15:59
 */
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void invoke(){
        this.command.exe();
    }
}
