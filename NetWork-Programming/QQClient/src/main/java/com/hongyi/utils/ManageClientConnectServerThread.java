package com.hongyi.utils;

import com.hongyi.service.ClientConnectServerThread;

import java.util.HashMap;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:43
 * @description: TODO
 */
public class ManageClientConnectServerThread {

    static HashMap<String, ClientConnectServerThread> map = new HashMap();

    /**
     * 添加到map进行管理
     *
     * @param userId                    用户id
     * @param clientConnectServerThread 当前线程
     */
    public static void addClientConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread) {
        map.put(userId, clientConnectServerThread);
    }

    /**
     * 根据用户id或者当前线程
     *
     * @param userId
     * @return
     */
    public static ClientConnectServerThread getClientConnectServerThread(String userId) {
        return map.get(userId);
    }
}
