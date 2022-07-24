package com.hongyi.socket.tcp.byByte;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 15:20
 * @description: 客户端，发送 "hello, server" 给服务端
 */
public class SocketTCP01Client {
    public static void main(String[] args) throws IOException {
        //一、连接服务器(ip,port); //解读:连接本机的9999端口,如果成功,返回Socket对象;
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端socket返回 = "+socket.getClass());
        //二、连接上后,生成Socket,通过socket.getOutputStream()//得到和socket对象关联的输出流对象;
        OutputStream outputStream = socket.getOutputStream();
        //三、通过输出流，写入数据到 数据通道
        outputStream.write("hello,server".getBytes());
        //四、关闭流对象和socket，必须关闭
        outputStream.close();
        socket.close();
        System.out.println("客户端退出......");
    }
}
