package org.akboom.blogapi.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.akboom.blogapi.dao.pojo.SysUser;
import org.akboom.blogapi.dao.pojo.Tag;

import java.util.List;

/**
 * @Classname ArticleVo
 * @Description Article与前端的数据交互对象
 * @Author AoLinChen
 */
@Data
@JsonIgnoreProperties(value = { "handler" })
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

    private ArticleBodyVo body;

    private List<Tag> tags;

    private List<CategoryVo> categories;
}
