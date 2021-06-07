package com.cc.learn.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

/**
 * 通过客户端程序进行远程访问 配置
 *
 * @author wangchen
 * @createDate 2021/06/04
 */
public class HelloAgentServer {
    public static void main(String[] args) throws Exception{
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmx:name=hello");
        //create bean and register bean
        platformMBeanServer.registerMBean(new Hello(),helloName);

        //这个步骤很重要，注册一个端口，绑定url后，用于客户端通过rmi方式连接JMXConnectorServer
        LocateRegistry.createRegistry(9999);
        //URL路径的结尾可以随意指定，但如果需要用JConsole连接，则必须使用jmxrmi
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url,null,platformMBeanServer);
        System.out.println("begin rmi start");
        jcs.start();
        System.out.println("rmi start");
    }
}
