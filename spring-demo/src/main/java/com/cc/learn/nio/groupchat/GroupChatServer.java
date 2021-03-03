package com.cc.learn.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 群聊系统服务端
 *
 * @author wangchen
 * @createDate 2021/03/01
 */
public class GroupChatServer {

    public static int PORT = 6666;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    //初始化
    public GroupChatServer() throws IOException {
        //得到选择器
        selector = Selector.open();
        //得到serverSocketChannel
        serverSocketChannel = ServerSocketChannel.open();
        //绑定端口
        serverSocketChannel.bind(new InetSocketAddress(PORT));
        //设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //注册到选择器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    //监听通道
    public void listen() throws IOException {
        System.out.println("监听线程： " + Thread.currentThread().getName());
        //循环处理
        while (true) {
            int select = selector.select();
            if (select > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                //有事件发生
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    //客户端连接事件
                    if (selectionKey.isAcceptable()) {
                        //获取客户端连接通道
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        //设置非阻塞
                        socketChannel.configureBlocking(false);
                        //注册到选择器上，并监听读事件
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        //提示上线
                        System.out.println(socketChannel.getRemoteAddress() + " 上线");
                    }
                    //读事件
                    if (selectionKey.isReadable()) {
                        //处理读
                        readMsg(selectionKey);
                    }
                }
                //清除key,防止重复
                iterator.remove();
            } else {
                System.out.println("没有事件发生。。。");
            }
        }
    }

    //处理读
    private void readMsg(SelectionKey selectionKey) {
        System.out.println("readMsg线程： " + Thread.currentThread().getName());
        SocketChannel channel = null;
        try {
            //获取通道
            channel = (SocketChannel) selectionKey.channel();
            //获取ByteBuffer
            ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
            //将通道中的消息读取到buffer中
            int read = channel.read(byteBuffer);
            if (read > 0) {
                String msg = new String(byteBuffer.array());
                System.out.println("客户端 " + channel.getRemoteAddress() + "说： " + msg);
                //广播消息（除了自己）
                sendMsgToOtherClients(channel, msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                System.out.println(channel.getRemoteAddress() + "离线了");
                //取消注册
                selectionKey.cancel();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //向其他客户端发送消息
    private void sendMsgToOtherClients(SocketChannel channel, String msg) {
        selector.keys().forEach(key -> {
            Channel channel1 = key.channel();
            if (channel1 instanceof SocketChannel && channel1 != channel) {
                try {
                    //向其他客户端发送消息
                    ((SocketChannel) channel1).write(ByteBuffer.wrap(msg.getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
