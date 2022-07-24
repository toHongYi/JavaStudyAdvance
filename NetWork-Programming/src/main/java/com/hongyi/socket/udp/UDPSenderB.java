package com.hongyi.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 20:54
 * @description: 发送端 B 也可以接收数据
 */
@SuppressWarnings({"all"})
public class UDPSenderB {
    public static void main(String[] args) throws Exception {
        // 1、创建DatagramSocket 对象,准备在9998端口接收数据;
        DatagramSocket socket = new DatagramSocket(9998);
        // 2、将需要发生的数据,封装到 DatagramSocket 对象
        byte[] data = "hello~~明天吃火锅".getBytes();
        // 说明: 封装的 DatagramSocket 对象data内容字节数组 ,data.length, 主机(IP),端口
        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("192.168.1.84"), 9999);
        socket.send(packet);

        // 3、=== 接收从 A 端回复的信息
        // (1) 构建一个DatagramPacket 对象,准备接收数据
        // 在前面讲解 UDP 协议时,老师说过一个数据包最大 64k;
        byte[] buf = new byte[1024];
        packet = new DatagramPacket(buf, buf.length);
        // (2) 调用接收方法,将通过网络通传输的 DatagramPacket() 对象
            // 填充到 packet 对象
            // 提示: 当有数据包发送到本地的9998端口,就会接收到数据
            // 如果没有数据包发送到 本机的 9998 端口,就会消息阻塞
        socket.receive(packet);
        // (3) 可以把 packet 进行拆包,取出数据, 并显示
        int length = packet.getLength();//实际接收到的数据字节长度
        data = packet.getData();//接收到数据
        String s = new String(data, 0, length);
        System.out.println(s);
        //关闭资源
        socket.close();
        System.out.println("B 端退出");

    }
}
