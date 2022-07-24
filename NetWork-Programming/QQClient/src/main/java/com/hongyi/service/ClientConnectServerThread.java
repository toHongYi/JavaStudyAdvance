package com.hongyi.service;

import com.hongyi.entity.Message;
import com.hongyi.entity.MsgType;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:44
 * @description: TODO
 */
public class ClientConnectServerThread extends Thread {

    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //循环读取消息。如果没有设置whine只会读一次就结束了
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                //根据消息类型判断
                if (message.getMsgType().equals(MsgType.MESSAGE_GET_ONLINE)) {
                    String[] s = message.getContent().split(" ");
                    for (String s1 : s) {
                        System.out.println("在线用户：" + s1);
                    }
                } else if (MsgType.MESSAGE_COMM_MES.equals(message.getMsgType())) {
                    //私聊
                    System.out.println(message.getSender() + "对" + message.getGetter() + "说：" + message.getContent());
                } else if (MsgType.MESSAGE_COMM_MES_ALL.equals(message.getMsgType())) {
                    //群聊
                    System.out.println(message.getSender() + "对大家说：" + message.getContent());
                } else if (message.getMsgType().equals(MsgType.MESSAGE_FILE_MES)) {
                    //文件
                    System.out.println(message.getSender() + "发送文件给" + message.getGetter());
                    FileOutputStream fos = new FileOutputStream(message.getDest());
                    fos.write(message.getFile());
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}
