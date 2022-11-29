package org.akboom.blogapi.vo.param;

import lombok.Data;

/**
 * @Classname ArticleBodyParam
 * @Description 文章主体交互对象
 * @Author AoLinChen
 */
@Data
public class ArticleBodyParam {

    private String content;

    private String contentHtml;
}
