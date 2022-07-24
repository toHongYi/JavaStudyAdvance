package com.hongyi.entity;

import java.io.Serializable;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:51
 * @description: TODO
 */
public class Message implements Serializable {
    /**
     * 发送者
     */
    private String sender;

    /**
     * 接收者
     */
    private String getter;

    /**
     * 内容
     */
    private String content;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 文件
     */
    private byte[] file;

    /**
     * 目标路径
     */
    private String dest;

    /**
     * 文件长度
     */
    private int fileLen = 0;
    /**
     * 原路径
     */
    private String src;

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    private String sendTime;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
