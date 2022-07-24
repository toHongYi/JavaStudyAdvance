package com.hongyi.service;

import com.hongyi.entity.Message;
import com.hongyi.entity.MsgType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:56
 * @description: 服务器推送新闻类
 */
public class SendNewsToAllService implements Runnable{

    public void run() {
        while (true) {
            System.out.println("请输入需要推送的新闻：");
            String text = new Scanner(System.in).next();
            //exit退出当前线程
            if ("exit".equals(text)) {
                break;
            }
            Message message = new Message();
            message.setContent(text);
            message.setMsgType(MsgType.MESSAGE_COMM_MES_ALL);
            message.setSender("服务器");
            message.setSendTime(new Date().toString());
            //获取所有在线的线程
            HashMap<String, ServerConnectServerThread> map = ManageServerConnectServerThread.map;
            for (String s : map.keySet()) {
                //遍历每个服务端管理的线程挨个给客户端发送消息，实现群发
                ServerConnectServerThread serverConnectServerThread = map.get(s);
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectServerThread.getSocket().getOutputStream());
                    oos.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
