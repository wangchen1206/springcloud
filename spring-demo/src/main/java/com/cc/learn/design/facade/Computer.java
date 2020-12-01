package com.cc.learn.design.facade;

/**
 * 外观模式
 * 外观模式是为了解决类与类之家的依赖关系的，像spring一样，可以将类和类之间的关系配置到配置文件中，
 * 而外观模式就是将他们的关系放在一个Facade类中，降低了类类之间的耦合度，该模式中没有涉及到接口
 *
 * @Author: cc
 * @Date: 2019/11/25 16:49
 */
public class Computer {

    private CPU cpu;
    private Memory memory;
    private Disk disk;

    public Computer() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.disk = new Disk();
    }

    public void startup(){
        System.out.println("Computer start startup");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("Computer finish startup");
    }

    public void shutdown(){
        System.out.println("Computer start shutdown");
        cpu.shutdowm();
        memory.shutdown();
        disk.shutdown();
        System.out.println("Computer finish shutdown");
    }
}
