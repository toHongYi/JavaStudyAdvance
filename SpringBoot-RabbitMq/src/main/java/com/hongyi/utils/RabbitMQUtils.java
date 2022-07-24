package com.hongyi.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/21 20:48
 * @description: TODO
 */
public class RabbitMQUtils {

    /**
     * 获取rabbitMQ连接
     * @return
     *
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //rabbit服务端口
        connectionFactory.setHost("175.24.201.192");
        connectionFactory.setPort(5672);

        //用户名、密码
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        // VirtualHost 确定消息存储的位置 类似文件夹
        connectionFactory.setVirtualHost("/");


        return  connectionFactory.newConnection();
    }
}
