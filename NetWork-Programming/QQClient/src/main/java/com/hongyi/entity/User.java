package com.hongyi.entity;

import java.io.Serializable;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:34
 * @description: 用户实体
 */
public class User implements Serializable {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户密码
     */
    private String password;

    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
