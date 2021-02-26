package com.cc.learn.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * 聊天室 Server端
 *
 * @author wangchen
 * @createDate 2021/01/14
 */

/**
 * 疑惑：ServerSocketChannel 和 SocketChannel 利用Selector ,进行自己通道的对某一个事件的监听。
 * SelectionKey是通道和选择器的绑定关系，通道注册到选择器上，生成一个SelectionKey,并将通道绑定再SelectionKey上
 **/


public class ChatServer {
    private Selector selector;
    private ServerSocketChannel listenerChannel;
    private static int PORT = 9999;

    public ChatServer() {
        //获取选择器
        try {
            selector = Selector.open();
            //打开监听通道
            listenerChannel = ServerSocketChannel.open();
            //绑定端口
            listenerChannel.bind(new InetSocketAddress(PORT));
            //设置为非阻塞模式
            listenerChannel.configureBlocking(false);
            //将选择器绑定到通道，并监听accept事件
            listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
            printInfo("Chat Server is ready...");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start() {
        //不停轮询
        while (true) {
            //获取就绪的channel
            int count = 0;
            try {
                count = selector.select();
                if (count > 0) {
                    //获取 已经有监听事件发生的 selectionKey
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        //监听到accept
                        if (key.isAcceptable()) {
                            //获取连接
                            SocketChannel socketChannel = listenerChannel.accept();
                            //设置非阻塞模式
                            socketChannel.configureBlocking(false);
                            //注册到选择器，并监听 read
                            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                            System.out.println(socketChannel.getRemoteAddress().toString().substring(1) + "上线了...");
                            //将Selectkey设置为accept,接着准备接收其他客户端的请求
                            key.interestOps(SelectionKey.OP_ACCEPT);
                        }
                        //监听到read
                        if (key.isReadable()) {
                            //读取客户端发来的文件
                            readMsg(key);
                        }
                        //把当前key删掉，防止重复处理。
                        iterator.remove();
                    }
                } else {
                    System.out.println("独自在寒风中等候...");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readMsg(SelectionKey key) {
        SocketChannel channel = null;
        try {
            //得到关联的channel
            channel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
            //从通道中读取数据并放入缓冲区
            int count = channel.read(byteBuffer);
            //如果读取到了数据
            if (count > 0) {
                //把缓冲区的数据转换为字符串
                String msg = new String(byteBuffer.array());
                printInfo(msg);
                //将关联的channel设置为read，继续准备接受新数据
                key.interestOps(SelectionKey.OP_READ);
                //向所有客户端广播数据
                BroadCast(channel, msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void BroadCast(SocketChannel expect, String msg) {
        try {
            System.out.println("发送广播...");
            //广播数据到所有的channel
            for (SelectionKey key : selector.keys()) {
                Channel targetChannel = key.channel();
                //排除自身
                if (targetChannel instanceof SocketChannel && targetChannel != expect) {
                    SocketChannel dest = (SocketChannel) targetChannel;
                    //把数据存储到缓冲区
                    ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                    //往通道中写数据
                    dest.write(wrap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printInfo(String s) {
        System.out.println("[" + LocalDateTime.now().toString() + "]-> " + s);
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.start();
    }
}
