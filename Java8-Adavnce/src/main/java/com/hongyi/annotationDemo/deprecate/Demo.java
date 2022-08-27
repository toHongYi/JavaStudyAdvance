package com.hongyi.annotationDemo.deprecate;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/4 22:04
 * @description: TODO
 */
public class Demo {

    @SuppressWarnings("")
    public static void main(String[] args) {
        Hero hero = new Hero();
        hero.say();
        hero.speak();

    }
}
