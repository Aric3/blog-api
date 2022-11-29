package org.akboom.blogapi.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @Classname TagVo
 * @Description Tag与前端的数据交互对象
 * @Author AoLinChen
 */
@Data
@JsonIgnoreProperties(value = { "handler" })
public class TagVo {

    private long id;

    private String tagName;
}
