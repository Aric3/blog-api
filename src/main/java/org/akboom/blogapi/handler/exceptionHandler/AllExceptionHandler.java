package org.akboom.blogapi.handler.exceptionHandler;

import org.akboom.blogapi.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname AllExceptionHandler
 * @Description 使用AOP对加了Controller的类进行拦截 处理异常
 * @Author AoLinChen
 */
@ControllerAdvice
public class AllExceptionHandler {

    /**
     * @Description 处理Exception异常 返回json数据
     * @param e
     * @return Result
     */

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doException(Exception e){
        e.printStackTrace();
        /*TODO:日志记录*/
        return Result.fail(999,"系统异常");
    }

}
