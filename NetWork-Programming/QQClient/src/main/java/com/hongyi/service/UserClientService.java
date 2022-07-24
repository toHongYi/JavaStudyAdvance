package com.hongyi.service;

import com.hongyi.entity.Message;
import com.hongyi.entity.MsgType;
import com.hongyi.entity.User;
import com.hongyi.utils.ManageClientConnectServerThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:38
 * @description: TODO
 */
public class UserClientService {
    private static Socket socket = null;

    /**
     * 校验用户
     *
     * @param user
     * @return
     */
    public static boolean checkUser(User user) {
        boolean flag = false;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();
            if (message.getMsgType().equals(MsgType.MESSAGE_LOGIN_SUCCESS)) {
                //校验成功开启一个新的客户端线程循环读取信息
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
                clientConnectServerThread.start();
                //写入map进行管理
                ManageClientConnectServerThread.addClientConnectServerThread(user.getUserId(), clientConnectServerThread);
                flag = true;
            } else {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 退出
     *
     * @param userId
     */
    public static void logout(String userId) {
        Message message = new Message();
        message.setMsgType(MsgType.MESSAGE_EXIT);
        message.setSender(userId);
        try {
            //通知服务端退出线程的操作
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(userId).getSocket().getOutputStream());
            oos.writeObject(message);
            System.out.println(userId + "退出了系统");
            //正常结束
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
