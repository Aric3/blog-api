package org.akboom.blogapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Classname Result
 * @Description 返回结果类
 * @Author AoLinChen
 */
@Data
@AllArgsConstructor
public class Result {

    private boolean success;

    private int code;

    private String message;

    private Object data;

    public static Result success(Object data){
        return new Result(true,200,"success", data);
    }

    public static Result fail(int code, String msg){
        return new Result(false, code, msg, null);
    }

}
