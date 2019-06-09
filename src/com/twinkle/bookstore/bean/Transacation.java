package com.twinkle.bookstore.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Name com.twinkle.bookstore.bean/Transacation.java
 * @Description: 事务控制、注解
 *
 * @VersionInformation: Twinkle 2019年5月19日 V1.0
 */
@Retention(RetentionPolicy.RUNTIME)
//运行时开始
@Target(value=ElementType.METHOD)
//只能用在方法上
public @interface Transacation {

}
