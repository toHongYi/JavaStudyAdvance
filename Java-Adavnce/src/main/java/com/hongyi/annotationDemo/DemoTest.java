package com.hongyi.annotationDemo;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/8/4 22:11
 * @description: TODO
 */
@TestAnnotation2()
public class DemoTest {
    public static void main(String[] args) {
        boolean hasAnnotation = Test.class.isAnnotationPresent(TestAnnotation.class);
        if ( hasAnnotation ) {
            TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);
            System.out.println("id:"+testAnnotation.id());
            System.out.println("msg:"+testAnnotation.msg());
        }
    }
}

