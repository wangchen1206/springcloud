package com.cc.learn.jmx;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * https://www.cnblogs.com/dongguacai/p/5900507.html  Jmx
 *
 * @author wangchen
 * @createDate 2021/06/04
 */
public class HelloAgent {
    public static void main(String[] args) throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmx:name=hello");
        //create bean and register bean
        platformMBeanServer.registerMBean(new Hello(),helloName);

        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082");
        HtmlAdaptorServer htmlAdaptorServer = new HtmlAdaptorServer();
        platformMBeanServer.registerMBean(htmlAdaptorServer,adapterName);
        htmlAdaptorServer.start();
//        Thread.sleep(60*60*1000);
    }
}
