package org.akboom.blogapi.vo.param;

import lombok.Data;

/**
 * @Classname LoginParam
 * @Description 登录请求 数据对象
 * @Author AoLinChen
 */
@Data
public class LoginParam {

    private String account;

    private String password;

    private String nickname;
}
