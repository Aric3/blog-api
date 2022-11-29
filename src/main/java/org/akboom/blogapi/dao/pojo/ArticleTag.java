package org.akboom.blogapi.dao.pojo;

import lombok.Data;

/**
 * @Classname ArticleTag
 * @Description 文章标签关联类持久化对象
 * @Author AoLinChen
 */
@Data
public class ArticleTag {

    private Long id;

    private Long articleId;

    private Long tagId;
}
