package com.cc.learn.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

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
public class TestNonBlockingNIO {

    //客户端
    @Test
    public void client() throws IOException {
        //1. 获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //2. 切换非阻塞模式
        sChannel.configureBlocking(false);

        //3. 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //4. 发送数据给服务端
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            String str = scan.next();
            buf.put((new Date().toString() + "\n" + str).getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }

        //5. 关闭通道
        sChannel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        //1. 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2. 切换非阻塞模式
        ssChannel.configureBlocking(false);

        //3. 绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        //4. 获取选择器
        Selector selector = Selector.open();

        //5. 将通道注册到选择器上, 并且指定“监听接收事件”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6. 轮询式的获取选择器上已经“准备就绪”的事件
        while (selector.select() > 0) {

            //7. 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()) {
                //8. 获取准备“就绪”的事件
                SelectionKey sk = it.next();

                //9. 判断具体是什么事件准备就绪
                if (sk.isAcceptable()) {
                    //10. 若“接收就绪”，获取客户端连接
                    SocketChannel sChannel = ssChannel.accept();

                    //11. 切换非阻塞模式
                    sChannel.configureBlocking(false);

                    //12. 将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    //13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();

                    //14. 读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    while ((len = sChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }

                //15. 取消选择键 SelectionKey
                it.remove();
            }
        }
    }


    //非阻塞式client
    @Test
    public void nonBlockingClient() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞连接
        socketChannel.configureBlocking(false);

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9898);
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){//nio 作为非阻塞式的优势
                System.out.println("Client: 连接服务器的同时，我还可以做点别的事");
            }
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()){
			String str = scanner.next();
			byteBuffer.put((LocalDateTime.now().toString() + "\n" + str).getBytes());
			byteBuffer.flip();
			socketChannel.write(byteBuffer);
			byteBuffer.clear();
		}

		socketChannel.close();
	}

    //非阻塞式服务端
    @Test
    public void nonBlockingServer() throws IOException {
        //获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //配置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9898));
        //获取选择器
        Selector selector = Selector.open();
        //将通道注册到选择器上,并指定 "监听接收事件"
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //轮询获取选择器上"已经准备就绪"监听的事件
        while (true) {
            //监控客户端
            if (selector.select(2000) == 0){//非阻塞式的优势
                System.out.println("Server: 没有客户端搭理我，我就做点别的事");
                continue;
            }
        	//获取选择器上"所有注册的"的选择键
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

			while (iterator.hasNext()){
				SelectionKey selectionKey = iterator.next();
				//获取"接收连接"事件
                if (selectionKey.isConnectable()){
                    System.out.println("有客户端连接: "+selectionKey.channel());
                }else if (selectionKey.isAcceptable()){
					//获取客户端连接
					SocketChannel channel = serverSocketChannel.accept();
                    System.out.println("有客户端连接，地址是： "+channel.getRemoteAddress().toString());
					//配置 非阻塞模式
					channel.configureBlocking(false);
					//将通道注册到选择器上，并指定“可读”监听事件
					channel.register(selector,SelectionKey.OP_READ);
				}else if (selectionKey.isReadable()){
					//获取选择器上“读就绪”的通道
					SocketChannel channel = (SocketChannel) selectionKey.channel();
					//分配缓冲区
					ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
					//读取数据到缓冲区
					int len = 0;
					while ((len = channel.read(byteBuffer)) > 0){
						byteBuffer.flip();
						System.out.println(new String(byteBuffer.array(),0,len));
						byteBuffer.clear();
					}
				}
				//取消选择键，防止占用资源
				iterator.remove();
			}
		}
    }

	@Test
	public void test3(){
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()){
			String next = scanner.next();
			System.out.println(next);
		}
	}

	public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        //设置为非阻塞连接
        socketChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()){
            String str = scanner.next();
            byteBuffer.put((LocalDateTime.now().toString() + "\n" + str).getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        socketChannel.close();
	}
}
