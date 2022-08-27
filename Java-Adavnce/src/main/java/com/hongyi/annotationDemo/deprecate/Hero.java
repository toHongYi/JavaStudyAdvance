package com.hongyi.annotationDemo.deprecate;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/4 22:02
 * @description:
 */
public class Hero {

    @Deprecated
    public void say() {
        System.out.println("Nothing has to say!");
    }

    public void speak() {
        System.out.println("I have A dream");
    }

}
