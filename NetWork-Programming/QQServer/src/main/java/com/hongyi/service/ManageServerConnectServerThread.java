package com.hongyi.service;

import java.util.HashMap;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:58
 * @description: 服务端线程管理类
 */
public class ManageServerConnectServerThread {
    static HashMap<String, ServerConnectServerThread> map = new HashMap();

    /**
     * 添加到map进行管理
     *
     * @param userId                    用户id
     * @param serverConnectServerThread 当前线程
     */
    public static void addServerConnectServerThread(String userId, ServerConnectServerThread serverConnectServerThread) {
        map.put(userId, serverConnectServerThread);
    }

    /**
     * 根据用户id或者当前线程
     *
     * @param userId
     * @return
     */
    public static ServerConnectServerThread getServerConnectServerThread(String userId) {
        return map.get(userId);
    }

    /**
     * 根据用户id删除当前管理的线程
     *
     * @param userId
     */
    public static void removeServerConnectServerThread(String userId) {
        map.remove(userId);
    }
}
