package org.akboom.blogapi.aop;

import java.lang.annotation.*;

/**
 * @Classname Cache
 * @Description redis缓存注解
 * @Author AoLinChen
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    /*过期时间 默认一分钟*/
    long expire() default 1 * 60 * 1000;

    /*键值组成部分*/
    String name() default "";

}
