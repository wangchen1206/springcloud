package com.cc.learn.jmx;

import javax.management.Attribute;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * 客户端程序，用于与agent进行远程连接
 * 远程连接HelloAgentServer
 *
 * @author wangchen
 * @createDate 2021/06/04
 */
public class Client {
    public static void main(String[] args) throws Exception{
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
        //ObjectName与服务端注册的保持一致
        ObjectName mbeanName = new ObjectName("jmx:name=hello");

        System.out.println("Domains。。。。。");
        String[] domains = mbsc.getDomains();
        for (int i = 0; i < domains.length; i++) {
            System.out.println("domain["+i+"]="+domains[i]);
        }
        System.out.println("MBean count = "+mbsc.getMBeanCount());

        //设置MBean的属性值
        //这里的setAttribute、getAttribute操作只能针对bean的属性
        //例如对getName或者setName进行操作，只能使用Name,需要去除方法的前缀
        mbsc.setAttribute(mbeanName,new Attribute("Name","CC"));
        mbsc.setAttribute(mbeanName,new Attribute("Age","18"));
        //获取属性值
        String age = (String) mbsc.getAttribute(mbeanName, "Age");
        String name = (String) mbsc.getAttribute(mbeanName, "Name");
        System.out.println("age="+age+";name="+name);

        HelloMBean helloMBean = MBeanServerInvocationHandler.newProxyInstance(mbsc, mbeanName, HelloMBean.class, false);
        helloMBean.getTelephone();
        helloMBean.helloWorld();
        helloMBean.helloWorld("cc");
        //invoke调用bean的方法，只针对非设置属性的方法
        //例如invoke不能对getName方法进行调用
        mbsc.invoke(mbeanName,"getTelephone",null,null);
        mbsc.invoke(mbeanName,"helloWorld",
                new String[]{"I'll connect to JMX Server via client2"},new String[]{"java.lang.String"});
        mbsc.invoke(mbeanName,"helloWorld",null,null);
    }
}
