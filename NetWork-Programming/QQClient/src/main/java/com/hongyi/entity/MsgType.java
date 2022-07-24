package com.hongyi.entity;

import java.io.Serializable;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:35
 * @description: TODO
 */
public class MsgType implements Serializable {

    /**
     * 普通消息
     */
    public static final String MESSAGE_COMM_MES = "3";
    /**
     * 登录成功
     */
    public final static String MESSAGE_LOGIN_SUCCESS = "1";
    /**
     * 登录失败
     */
    public final static String MESSAGE_LOGIN_FAIL = "2";


    /**
     * 获取在线用户
     */
    public final static String MESSAGE_GET_ONLINE = "4";
    public final static String MESSAGE_RET_ONLINE = "5";
    /**
     * 退出
     */
    public final static String MESSAGE_EXIT = "6";
    /**
     * 群发
     */
    public final static String MESSAGE_COMM_MES_ALL = "7";
    /**
     * 文件消息
     */
    public final static String MESSAGE_FILE_MES = "8";
}
