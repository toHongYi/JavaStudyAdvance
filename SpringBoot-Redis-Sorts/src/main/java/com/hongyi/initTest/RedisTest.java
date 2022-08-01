package com.hongyi.initTest;

import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 *
 * 使用 java-redis 客户端  连接redis  在实际开发中用的非常少？
 *
 * 因为很多时候，可能软件的业务在使用redis 出现问题，不知道是软件的问题 还是 redis 本身问题
 *
 * 此时我们就是使用 最简化的java 去连接测试redis
 */
public class RedisTest {

    private  Jedis jedis;

    @Before  // 初始化资源
    public void  init(){

        System.out.println("init");

        // "192.168.12.135", 6379 连接linux 端口 就是连接 redis 容器端口
        jedis = new Jedis("175.24.201.192", 6379);
        jedis.auth("redis123456");
    }

    @Test  // 单元测试
    public void  test(){

        System.out.println("test");
    }


    @Test// set key value
    public void setTest(){

        String result = jedis.set("name", "xiaoming");
        System.out.println("result = " + result);

    }

    @Test // get key
    public void  getTest(){

        String value = jedis.get("name");

        System.out.println("value = " + value);

    }


    @Test // 使用 hash  存对象
    public void  saveObject1(){

        User user = new User();
        user.setId(100);
        user.setName("xiaoming");


        jedis.hset("user1", "name", user.getName());

        // user.getId()+""  将int  直接转化为 字符串
        jedis.hset("user1", "id", user.getId()+"");


        Map<String, String> map = jedis.hgetAll("user1");
        System.out.println("map = " + map);
        // map ---》 user


    }

    @Test // 使用 String  存对象
    public void  saveObject2(){

        User user = new User();
        user.setId(100);
        user.setName("xiaoming");

        // JSON.toJSONString(user)  将对象转化 json 字符串
        jedis.set("user2", JSON.toJSONString(user));



        // 读取 json 字符串 转化为  对象
        String str = jedis.get("user2");
        User userJson = JSON.parseObject(str,User.class);
        System.out.println("userJson = " + userJson);

    }



    @After  // 测试完毕销毁资源
    public void  destory(){

        System.out.println("destory");

        // 释放资源
        jedis.close();
    }


}
