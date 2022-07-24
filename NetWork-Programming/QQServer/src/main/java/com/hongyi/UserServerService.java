package com.hongyi;

import com.hongyi.entity.Message;
import com.hongyi.entity.MsgType;
import com.hongyi.entity.User;
import com.hongyi.service.ManageServerConnectServerThread;
import com.hongyi.service.SendNewsToAllService;
import com.hongyi.service.ServerConnectServerThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:55
 * @description: TODO
 */
public class UserServerService {
    private ServerSocket serverSocket = null;

    private static HashMap<String, User> map = new HashMap();


    /**
     * 初始化用户
     */
    static {
        map.put("10010", new User("10010", "123456"));
        map.put("10020", new User("10020", "123456"));
        map.put("10030", new User("10030", "123456"));
        map.put("10040", new User("10040", "123456"));
    }

    /**
     * 校验用户信息
     *
     * @param userId
     * @param password
     * @return
     */
    private boolean checkUser(String userId, String password) {
        User user = map.get(userId);
        if (null == user) {
            return false;
        }
        if (!user.getPassword().equals(password)) {
            return false;
        }
        return true;
    }

    public UserServerService() {
        System.out.println("服务端在8888端口监听.....");
        //开启一个新的线程实现服务端推送新闻
        new Thread(new SendNewsToAllService()).start();
        try {
            serverSocket = new ServerSocket(9999);
            //循环监听客户端连接
            while (true) {
                Socket socket = serverSocket.accept();
                //获取客户端发送的消息
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User user = (User) ois.readObject();
                Message message = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //验证用户名密码
                if (checkUser(user.getUserId(), user.getPassword())) {
                    //登录成功设置消息类型为成功
                    message.setMsgType(MsgType.MESSAGE_LOGIN_SUCCESS);
                    //写回客户端
                    oos.writeObject(message);
                    //重新开启一个新的监听线程进行与客户端的连接
                    ServerConnectServerThread serverConnectServerThread = new ServerConnectServerThread(socket, user.getUserId());
                    serverConnectServerThread.start();
                    //放入map中进行管理
                    ManageServerConnectServerThread.addServerConnectServerThread(user.getUserId(), serverConnectServerThread);
                } else {
                    //登录失败也写回客户端
                    message.setMsgType(MsgType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                    serverSocket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new UserServerService();
    }
}
