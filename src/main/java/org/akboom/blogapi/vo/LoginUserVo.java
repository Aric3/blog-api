package org.akboom.blogapi.vo;

import lombok.Data;

/**
 * @Classname LoginUserVo
 * @Description 当前登录用户数据对象
 * @Author AoLinChen
 */
@Data
public class LoginUserVo {
    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}
