package com.hongyi.annotationDemo;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation2 {

    int id() default -1;
    String name() default "hongYi";

}

@TestAnnotation2(id = 2,name = "flyInSky")
class Test2{

}
