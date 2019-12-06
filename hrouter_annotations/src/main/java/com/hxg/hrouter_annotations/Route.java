package com.hxg.hrouter_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//声明注解是放在什么上面的
@Target(ElementType.TYPE)
//声明注解存在的周期
@Retention(RetentionPolicy.CLASS)
public @interface Route {
    String value();
}
