package org.akboom.blogapi.aop;

/**
 * @Classname Logging
 * @Description 自定义日志注解
 * @Author AoLinChen
 */

import java.lang.annotation.*;

/**
 * 日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logging {

    String module() default "";

    String operation() default "";
}
