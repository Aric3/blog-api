package org.akboom.blogapi.vo.param;

import lombok.Data;
import org.akboom.blogapi.vo.CategoryVo;
import org.akboom.blogapi.vo.TagVo;

import java.util.List;

/**
 * @Classname ArticleParam
 * @Description 前端传入文章交互对象
 * @Author AoLinChen
 */
@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}
