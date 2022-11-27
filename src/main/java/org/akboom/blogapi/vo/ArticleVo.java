package org.akboom.blogapi.vo;

import lombok.Data;

import java.util.List;

/**
 * @Classname ArticleVo
 * @Description Article与前端的数据交互对象
 * @Author AoLinChen
 */
@Data
public class ArticleVo {

    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    private int weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

    //private ArticleBodyVo body;

    private List<TagVo> tags;

    //private List<CategoryVo> categories;
}
