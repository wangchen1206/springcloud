package com.cc.learn.nio;


import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * FileChannel不是 非阻塞IO
 *
 * @author wangchen
 * @createDate 2021/01/11
 */
public class TestFileChannel {

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
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        byteBuffer.flip();
        System.out.println("-----------------------------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());
        byte[] dst = new byte[10];
        byteBuffer.get(dst,0,10);
        System.out.println(new String(dst));
        fileInputStream.close();
    }

    //复制文件
    @Test
    public void test3() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("test2.txt");
        FileChannel srcChannel = fileInputStream.getChannel();
        FileChannel descChannel = fileOutputStream.getChannel();
        descChannel.transferFrom(srcChannel,0,srcChannel.size());
        srcChannel.transferTo(0,srcChannel.size(),descChannel);
        srcChannel.close();
        descChannel.close();
    }

    //第二种创建FileChannel的方式 (直接缓冲区)
    @Test
    public void test4() throws IOException {
        FileChannel open = FileChannel.open(Paths.get("test.txt"), StandardOpenOption.READ);
        FileChannel open1 = FileChannel.open(Paths.get("test4.txt"), StandardOpenOption.WRITE,
                StandardOpenOption.READ, StandardOpenOption.CREATE);
        //transferTo底层使用零拷贝
        //在Linux系统，可以直接将一个文件发送到一个channel
        //在Windows系统，一次可以发送8M的文件，大于8M的文件需要分次发送。
        open.transferTo(0,open.size(),open1);
        open.close();
        open1.close();
    }

    //FileChannel 内存映射文件
    @Test
    public void test5() throws IOException{
        FileChannel open = FileChannel.open(Paths.get("test.txt"), StandardOpenOption.READ);
        FileChannel open1 = FileChannel.open(Paths.get("test5.txt"), StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        MappedByteBuffer map = open.map(FileChannel.MapMode.READ_ONLY, 0, open.size());
        MappedByteBuffer map1 = open1.map(FileChannel.MapMode.READ_WRITE, 0, open.size());
        byte[] dst = new byte[map.limit()];
        map.get(dst);
        map1.put(dst);

        open.close();
        open1.close();
    }

    //分散读取和聚集写入
    @Test
    public void test6() throws IOException{
        RandomAccessFile randomAccessFile = new RandomAccessFile("test.txt", "rw");
        RandomAccessFile randomAccessFile1 = new RandomAccessFile("test6.txt", "rw");
        ByteBuffer allocate = ByteBuffer.allocate(10);
        ByteBuffer allocate1 = ByteBuffer.allocate(10);
        FileChannel channel = randomAccessFile.getChannel();
        ByteBuffer[] bufs = {allocate,allocate1};
        //分散读取
        channel.read(bufs);
        //讲position置为0，limit置为position，切换成读模式
        Arrays.stream(bufs).forEach(e->e.flip());

        FileChannel channel1 = randomAccessFile1.getChannel();
        //聚集写入
        channel1.write(bufs);
        channel.close();
        channel1.close();
    }

    //直接在内存映射中修改文件
    @Test
    public void test7() throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("test.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, randomAccessFile.length());
        map.put(0,(byte) 'Q');
        map.put(5,(byte) 'L');
        System.out.println("修改完毕！");
    }
}
