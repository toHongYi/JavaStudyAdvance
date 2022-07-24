package com.hongyi.service;

import com.hongyi.entity.Message;
import com.hongyi.entity.MsgType;
import com.hongyi.utils.ManageClientConnectServerThread;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:37
 * @description: TODO
 */
public class MessageClientService {
    public static void sendMessageToOne(String content, String sender, String getter) {
        Message message = new Message();
        message.setSender(sender);
        message.setGetter(getter);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        message.setMsgType(MsgType.MESSAGE_COMM_MES);
        System.out.println(sender + "对" + getter + "说" + content);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(sender).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageToAll(String content, String sender) {
        Message message = new Message();
        message.setSender(sender);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        message.setMsgType(MsgType.MESSAGE_COMM_MES_ALL);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(sender).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
