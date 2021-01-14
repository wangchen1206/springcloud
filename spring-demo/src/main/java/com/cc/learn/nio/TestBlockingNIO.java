package com.cc.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/*
 * 一、使用 NIO 完成网络通信的三个核心：
 * 
 * 1. 通道（Channel）：负责连接
 * 		
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 * 
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 * 
 * 2. 缓冲区（Buffer）：负责数据的存取
 * 
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 * 
 */
public class TestBlockingNIO {

	//客户端
	@Test
	public void client() throws IOException{
		//1. 获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
		
		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		
		//2. 分配指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		//3. 读取本地文件，并发送到服务端
		while(inChannel.read(buf) != -1){
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		//4. 关闭通道
		inChannel.close();
		sChannel.close();
	}
	
	//服务端
	@Test
	public void server() throws IOException{
		//1. 获取通道
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		
		FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		
		//2. 绑定连接
		ssChannel.bind(new InetSocketAddress(9898));
		
		//3. 获取客户端连接的通道
		SocketChannel sChannel = ssChannel.accept();
		
		//4. 分配指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		//5. 接收客户端的数据，并保存到本地
		while(sChannel.read(buf) != -1){
			buf.flip();
			outChannel.write(buf);
			buf.clear();
		}
		
		//6. 关闭通道
		sChannel.close();
		outChannel.close();
		ssChannel.close();
		
	}


	//客户端
	@Test
	public void client1() throws IOException {
		//获取网络通道
		SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

		//获取文件通道
		FileChannel fileChannel = FileChannel.open(Paths.get("test.txt"), StandardOpenOption.READ);

		//分配指定大小的非直接缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		//读取本地文件，并写入网络通道，发送到服务端
		while (fileChannel.read(byteBuffer) != -1){
			byteBuffer.flip();
			socketChannel.write(byteBuffer);
			byteBuffer.clear();
		}

		//关闭通道
		fileChannel.close();
		socketChannel.close();
	}

	//服务端
	@Test
	public void server1() throws IOException {
		//打开通道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		//绑定连接
		serverSocketChannel.bind(new InetSocketAddress(9898));
		//获取客户端连接
		SocketChannel socketChannel = serverSocketChannel.accept();

		//打开文件通道
		FileChannel fileChannel = FileChannel.open(Paths.get("test7.txt"), StandardOpenOption.WRITE,
				StandardOpenOption.CREATE);

		//分配指定大小的非直接缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		//读取客户端发来的文件，并写入本地文件
		while (socketChannel.read(byteBuffer) != -1){
			byteBuffer.flip();
			fileChannel.write(byteBuffer);
			byteBuffer.clear();
		}

		//关闭通道
		fileChannel.close();
		socketChannel.close();
		serverSocketChannel.close();
	}
	
}
