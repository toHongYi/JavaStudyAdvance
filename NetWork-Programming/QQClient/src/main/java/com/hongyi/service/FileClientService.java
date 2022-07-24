package com.hongyi.service;

import com.hongyi.entity.Message;
import com.hongyi.entity.MsgType;
import com.hongyi.utils.ManageClientConnectServerThread;

import java.io.FileInputStream;
import java.io.ObjectOutputStream;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 21:44
 * @description: TODO
 */
public class FileClientService {
    /**
     * @param src    原路径
     * @param dest   目标路径
     * @param sender 发送者
     * @param getter 接收者
     */
    public static void sendFieToOne(String src, String dest, String sender, String getter) {
        try {
            //文件读取
            FileInputStream fis = new FileInputStream(src);
            //fis.available()文件多大就设置多大
            byte[] buf = new byte[fis.available()];
            //写入buf
            fis.read(buf);
            Message message = new Message();
            message.setSender(sender);
            message.setGetter(getter);
            message.setFile(buf);
            message.setSrc(src);
            message.setDest(dest);
            message.setMsgType(MsgType.MESSAGE_FILE_MES);
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(sender).getSocket().getOutputStream());
            oos.writeObject(message);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
