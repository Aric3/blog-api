package org.akboom.blogapi.dao.pojo;

import lombok.Data;

/**
 * @Classname ArticleBody
 * @Description 文章主体持久化对象
 * @Author AoLinChen
 */
@Data
public class ArticleBody {

    private Long id;

    private String content;

    private String contentHtml;

    private Long articleId;
}
