package com.hongyi.annotationDemo;

import com.hongyi.annotationDemo.deprecate.Hero;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/4 21:41
 * @description: TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
    int id();

    String msg();
}

@TestAnnotation(id = 3, msg = "hello annotation")
class Test {

}

