package org.akboom.blogapi.vo;

import lombok.Data;

/**
 * @Classname UserVo
 * @Description 用户数据对象
 * @Author AoLinChen
 */
@Data
public class UserVo {

    private String nickname;

    private String avatar;

    private Long id;
}