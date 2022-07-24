package com.hongyi;

import com.hongyi.utils.RabbitMQUtils;
import com.rabbitmq.client.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class HelloRabbitTest {


    private Connection connection;


    @Before
    public void init() throws IOException, TimeoutException {

        connection = RabbitMQUtils.getConnection();
    }


    /**
     * 创建消费者
     * @throws IOException
     */
    @Test
    public void customer() throws IOException {


        Channel channel =  connection.createChannel();

        //3. 声明队列-HelloWorld
        //参数1：queue - 指定队列的名称
        //参数2：durable - 当前队列是否需要持久化（true）
        //参数3：exclusive - 是否排外（conn.close() - 当前队列会被自动删除，当前队列只能被一个消费者消费）
        //参数4：autoDelete - 如果这个队列没有消费者在消费，队列自动删除
        //参数5：arguments - 指定当前队列的其他信息
        channel.queueDeclare("hello-queue",true,false
                ,false,null);

        // 每次接受一条 消息
        channel.basicQos(1);


        Consumer consumer = new DefaultConsumer(channel){

            @Override // 接受消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                // body  接收到的消息

                String msg =  new String(body,"utf-8");
                System.out.println("消费者 接受 msg = " + msg);
            }
        };


        // 参数1 队列名
        // 参数2 自动ack
        // 参数3 消费者
        channel.basicConsume("hello-queue",true, consumer);

        //让 程序 卡住 一致消费消息
        System.in.read();

    }


    /**
     * 发布消息
     * @throws IOException
     * @throws TimeoutException
     */
    @Test
    public void  publishTest() throws IOException, TimeoutException {


        Channel channel =  connection.createChannel();

        // 参数1：指定exchange，使用""。
        // 参数2：指定路由的规则 也可以 是具体的队列名称。
        // 参数3：指定传递的消息所携带的properties，使用null。
        // 参数4：指定发布的具体消息，byte[]类型
        channel.basicPublish("", "hello-queue", null,"马上下课".getBytes());


        channel.close();
        connection.close();

    }

}
