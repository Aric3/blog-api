package org.akboom.blogapi.dao.pojo;

import lombok.Data;

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

