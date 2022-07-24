package com.hongyi.entity;

import java.io.Serializable;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:52
 * @description: TODO
 */
public class MsgType implements Serializable {
    /**
     * 登录成功
     */
    public static final String MESSAGE_LOGIN_SUCCESS = "1";
    /**
     * 登录失败
     */
    public static final String MESSAGE_LOGIN_FAIL = "2";
    /**
     * 普通消息
     */
    public static final String MESSAGE_COMM_MES = "3";
    /**
     * 获取在线用户
     */
    public static final String MESSAGE_GET_ONLINE = "4";
    public static final String MESSAGE_RET_ONLINE = "5";
    /**
     * 退出
     */
    public static final String MESSAGE_EXIT = "6";
    /**
     * 群发
     */
    public static final String MESSAGE_COMM_MES_ALL = "7";
    /**
     * 文件消息
     */
    public static final String MESSAGE_FILE_MES = "8";
}
