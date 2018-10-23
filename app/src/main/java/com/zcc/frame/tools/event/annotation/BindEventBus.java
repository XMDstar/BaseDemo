package com.zcc.frame.tools.event.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @creator : yangshuai
 * @create-time : 2018/8/23 上午9:57
 * @description :注解绑定Eventbus,只能作用于类
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface BindEventBus {

}
