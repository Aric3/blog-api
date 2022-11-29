package org.akboom.blogapi.dao.pojo;

import lombok.Data;

/**
 * @Classname Category
 * @Description 目录持久化对象
 * @Author AoLinChen
 */
@Data
public class Category {

    private Long id;

    private String avatar;

    private String categoryName;

    private String description;
}
