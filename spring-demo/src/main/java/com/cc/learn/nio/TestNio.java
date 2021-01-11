package com.cc.learn.nio;


import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description
 *
 * @author wangchen
 * @createDate 2021/01/11
 */
public class TestNio {

    //往本地文件写数据
    @Test
    public void test1() throws IOException {
        String str = "\\\nhello,nio,我是CC2";
        //获取文件的输出流
        FileOutputStream fileOutputStream = new FileOutputStream("test.txt",true);
        //获取channel
        FileChannel channel = fileOutputStream.getChannel();
        //初始化缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将数据放入缓冲区
        byteBuffer.put(str.getBytes());
        //翻转缓冲区，重置位置到初始位置
        byteBuffer.flip();
        //把缓冲区的数据写到通道中
        channel.write(byteBuffer);
        fileOutputStream.close();
    }

    //从本地文件读取数据
    @Test
    public void test2() throws IOException {
        //获取文件的输出流
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(((int) new File("test.txt").length()));
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

    //复制文件
    @Test
    public void test3() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("test1.txt");
        FileChannel srcChannel = fileInputStream.getChannel();
        FileChannel descChannel = fileOutputStream.getChannel();
        descChannel.transferFrom(srcChannel,0,srcChannel.size());
        srcChannel.close();
        descChannel.close();
    }
}
