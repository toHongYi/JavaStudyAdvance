package com.hongyi.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 15:15
 * @description: 地址工具获取指定互联网地址;
 */
public class InetAddressDemo {
    public static void main(String[] args) throws Exception {

        //获取本机 InetAddress 对象 getLocalHost
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost:"+localHost);
        //根据指定主机名/域名获取 ip 地址对象 getByName
//        InetAddress host2 = InetAddress.getByName("ThinkPad-PC");
//        System.out.println(host2);
        InetAddress host3 = InetAddress.getByName("www.tohongyi.fun");
        System.out.println(host3);
        //获取 InetAddress 对象的主机名 getHostName
        String host3Name = host3.getHostName();
        System.out.println(host3Name);
        //获取 InetAddress 对象的地址 getHostAddress
        String host3Address = host3.getHostAddress();
        System.out.println(host3Address);

    }
}
