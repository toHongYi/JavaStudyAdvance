package com.hongyi.service;

import com.hongyi.entity.Message;
import com.hongyi.entity.MsgType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:57
 * @description: 服务端与客户端建立连接通信的线程
 */
public class ServerConnectServerThread extends Thread {
    private Socket socket;

    private String userId;

    public ServerConnectServerThread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        try {
            //循环读取消息。如果没有设置whine只会读一次就结束了
            while (true) {
                System.out.println("服务器和客户端" + userId + "保持通讯");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //读取客户端的数据
                Message message = (Message) ois.readObject();
                //根据消息类型进行判断
                if (message.getMsgType().equals(MsgType.MESSAGE_GET_ONLINE)) {
                    System.out.println(message.getSender() + "要在线用户列表");
                    //获取服务端在线用户的map集合
                    HashMap<String, ServerConnectServerThread> map = ManageServerConnectServerThread.map;
                    String userId = "";
                    for (String s : map.keySet()) {
                        userId += s + " ";
                    }
                    message.setContent(userId);
                    //设置接收者
                    message.setGetter(message.getSender());
                    oos.writeObject(message);
                } else if (MsgType.MESSAGE_EXIT.equals(message.getMsgType())) {
                    //清理map中退出的用户
                    System.out.println(message.getSender() + "退出");
                    ManageServerConnectServerThread.removeServerConnectServerThread(userId);
                    socket.close();
                    break;
                } else if (MsgType.MESSAGE_COMM_MES.equals(message.getMsgType())) {
                    //私聊  通过获取接收者的socket进行给客户端发送消息
                    oos = new ObjectOutputStream(ManageServerConnectServerThread.getServerConnectServerThread(message.getGetter()).getSocket().getOutputStream());
                    oos.writeObject(message);
                } else if (MsgType.MESSAGE_COMM_MES_ALL.equals(message.getMsgType())) {
                    //群发
                    HashMap<String, ServerConnectServerThread> map = ManageServerConnectServerThread.map;
                    Iterator<String> iterator = map.keySet().iterator();
                    while (iterator.hasNext()) {
                        String userId = iterator.next();
                        //除了不发自己，其他人挨个发送
                        if (!userId.equals(message.getSender())) {
                            new ObjectOutputStream(map.get(userId).getSocket().getOutputStream()).
                                    writeObject(message);
                        }
                    }
                } else if (message.getMsgType().equals(MsgType.MESSAGE_FILE_MES)) {
                    //发送文件
                    new ObjectOutputStream(ManageServerConnectServerThread.getServerConnectServerThread(message.getGetter()).getSocket().getOutputStream()).writeObject(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
