package com.hongyi;

import com.hongyi.entity.Message;
import com.hongyi.entity.MsgType;
import com.hongyi.entity.User;
import com.hongyi.service.FileClientService;
import com.hongyi.utils.ManageClientConnectServerThread;
import com.hongyi.service.MessageClientService;
import com.hongyi.service.UserClientService;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:36
 * @description: TODO
 */
public class QQView {

    private boolean loop = true;

    private void mainMenu() {
        while (loop) {
            System.out.println("=============欢迎登陆网络通信系统===========");
            System.out.println("\t\t 1 登陆系统");
            System.out.println("\t\t 9 退出系统");
            System.out.println("请输入你的选择：");
            String key = new Scanner(System.in).next();
            switch (Integer.valueOf(key)) {
                case 1:
                    System.out.println("请输入你的用户号");
                    String userId = new Scanner(System.in).next();
                    System.out.println("请输入你的密码");
                    String password = new Scanner(System.in).next();
                    User user = new User(userId, password);
                    if (UserClientService.checkUser(user)) {
                        while (loop) {
                            System.out.println("========网络通信系统二级菜单 用户" + userId + "========");
                            System.out.println("1.显示在线用户列表\n2.群发信息\n3.私聊信息\n4.发送文件\n9.退出系统");
                            System.out.println("请输入你的选择：");
                            String key2 = new Scanner(System.in).next();
                            switch (Integer.valueOf(key2)) {
                                case 1:
                                    try {
                                        onlineFriendList(userId);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    System.out.println("请输入的你想对大家说的话：");
                                    String content = new Scanner(System.in).next();
                                    MessageClientService.sendMessageToAll(content, userId);
                                    break;
                                case 3:
                                    System.out.println("请输入的想聊天的用户号：");
                                    String id = new Scanner(System.in).next();
                                    System.out.println("请输入的你想说的话：");
                                    String text = new Scanner(System.in).next();
                                    MessageClientService.sendMessageToOne(text, userId, id);
                                    break;
                                case 4:
                                    System.out.println("请输入原文件路径");
                                    String src = new Scanner(System.in).next();
                                    System.out.println("请输入目的文件路径");
                                    String dest = new Scanner(System.in).next();
                                    System.out.println("请输入发送用户");
                                    String getter = new Scanner(System.in).next();
                                    FileClientService.sendFieToOne(src, dest, userId, getter);
                                    break;
                                default:
                                    loop = false;
                                    UserClientService.logout(userId);
                                    break;
                            }
                        }
                    } else {
                        System.out.println("账户名密码错误");
                    }
                    break;
                case 9:
                    loop = false;
                    break;
            }
        }
    }

    /**
     * 获取在线用户
     *
     * @param userId
     * @throws Exception
     */
    public void onlineFriendList(String userId) throws Exception {
        Message message = new Message();
        message.setMsgType(MsgType.MESSAGE_GET_ONLINE);
        message.setSender(userId);
        Socket socket = ManageClientConnectServerThread.getClientConnectServerThread(userId).getSocket();
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(message);
    }


    public static void main(String[] args) {
        new QQView().mainMenu();
    }
}
