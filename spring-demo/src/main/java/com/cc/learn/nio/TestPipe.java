package com.cc.learn.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

import org.junit.Test;

public class TestPipe {

    @Test
    public void test1() throws IOException {
        //1. 获取管道
        Pipe pipe = Pipe.open();

        //2. 将缓冲区中的数据写入管道
        ByteBuffer buf = ByteBuffer.allocate(1024);

        Pipe.SinkChannel sinkChannel = pipe.sink();
        buf.put("通过单向管道发送数据".getBytes());
        buf.flip();
        sinkChannel.write(buf);

        //3. 读取缓冲区中的数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        buf.flip();
        int len = sourceChannel.read(buf);
        System.out.println(new String(buf.array(), 0, len));

        sourceChannel.close();
        sinkChannel.close();
    }

    public static void main(String[] args) throws IOException {
        Pipe pipe = Pipe.open();

        Pipe.SinkChannel sink = pipe.sink();
        ByteBuffer byteBuffer = ByteBuffer.wrap("发送数据".getBytes());
        byteBuffer.flip();
        try {
            sink.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byteBuffer.flip();

        Pipe.SourceChannel source = pipe.source();
        try {
            source.read(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byteBuffer.flip();
        System.out.println(new String(byteBuffer.array()));
        source.close();
        sink.close();
    }
}
	
