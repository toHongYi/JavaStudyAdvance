package com.hongyi.springbootkafka.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version : V1.0
 * @date : 2022/3/16 13:00
 * @Author : LLH
 * @Desc :
 */
@RestController
@RequestMapping(value = "/v1")
public class HelloWorldController {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    @GetMapping("/helloWorld")
    public String roadShowInformDetails(){

        log.info("我们可以看到当前时间");
        log.error("这个是log.error报错信息");
        return "Hello-World";

    }
}
