package org.akboom.blogapi.vo;

import lombok.Data;

/**
 * @Classname TagVo
 * @Description Tag与前端的数据交互对象
 * @Author AoLinChen
 */
@Data
public class TagVo {

    private long id;

    private String tagName;
}
