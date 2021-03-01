package com.cc.learn.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 聊天室 Client
 *
 * @author wangchen
 * @createDate 2021/01/14
 */
public class ChatClient {
    private final String HOST = "127.0.0.1";
    private int PORT = 9999;
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    public ChatClient() {
        try {
            //获取选择器
            selector = Selector.open();
            //获取连接
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            //设置非阻塞
            socketChannel.configureBlocking(false);
            //注册选择器，并监听read
            socketChannel.register(selector, SelectionKey.OP_READ);
            //得到客户端IP地址和端口信息，作为聊天的用户名使用
            userName = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println("------Client(" + userName + ") is ready ------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //向服务器发送数据
    public void sendMsg(String msg) throws IOException {
        //如果控制台输入bye就关闭通道
        if (msg.equalsIgnoreCase("bye")) {
            socketChannel.close();
            socketChannel = null;
            return;
        }

        msg = userName + "说：" + msg;
        //往通道中写数据
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(byteBuffer);
    }

    //从服务端接收数据
    public void receiveMsg() throws IOException {
        int count = selector.select();
        if (count > 0 ){
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);
                    String msg = new String(byteBuffer.array());
                    System.out.println(msg.trim());
                }
                iterator.remove();//删除当前key，防止重复处理
            }
        }else {
            System.out.println("没人聊天了...");
        }
    }
}
