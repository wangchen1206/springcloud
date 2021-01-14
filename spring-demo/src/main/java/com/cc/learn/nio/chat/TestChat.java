package com.cc.learn.nio.chat;

import java.io.IOException;
import java.util.Scanner;

/**
 * 测试聊天室
 *
 * @author wangchen
 * @createDate 2021/01/14
 */
public class TestChat {
    public static void main(String[] args) {
        //创建一个聊天客户端
        ChatClient chatClient = new ChatClient();
        new Thread(()->{ //不断接收服务器发送的广播数据
            while (true){
                try {
                    chatClient.receiveMsg();
                    Thread.sleep(3000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        //在控制台输入数据，并发送到服务端
        while (scanner.hasNext()){
            String msg = scanner.nextLine();
            try {
                chatClient.sendMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
