package org.akboom.blogapi.dao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @Classname Tag
 * @Description 标签持久化对象
 * @Author AoLinChen
 */
@Data
public class Tag {
    /**
     * 主键
     */
    private Long id;

    /**
     * 标签图标
     */
    private String avatar;

    /**
     * 标签名称
     */
    private String tagName;
}
