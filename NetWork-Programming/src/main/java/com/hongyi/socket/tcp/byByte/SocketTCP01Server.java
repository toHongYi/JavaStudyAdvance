package com.hongyi.socket.tcp.byByte;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 15:19
 * @description:
 *      记得看一下源码,先开启监听等待连接
 */
public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
        //思路一、在本机的9999端口监听,等待链接;
            //细节:1、在本机没有其他服务在监听9999
            //    2、这个ServerSocket可以通过accept()返回多个Socket[多个客户端链接服务器并发]
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端,在9999端口监听,等待连接...");
        //二、当没有客户端连接9999端口时,程序会阻塞,等待连接;
        //    如果有客户端连接,则会返回socket对象,程序继续;
        Socket socket = serverSocket.accept();
        System.out.println("socket = " + socket.getChannel());

        //三、通过.getInputStream()读取客户端写入到数据通道的数据,显示
        InputStream inputStream = socket.getInputStream();

        //四、IO读取;
        byte[] buf = new byte[1024];
        int readLen = 0;
        while ((readLen = inputStream.read(buf))!=-1){
            System.out.println(new String(buf,0,readLen));
        }

        //五、关闭流和socket
        inputStream.close();
        socket.close();
        serverSocket.close();
        //关闭
    }
}
