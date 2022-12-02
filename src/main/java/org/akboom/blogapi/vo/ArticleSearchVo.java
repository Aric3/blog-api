package org.akboom.blogapi.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @Classname ArticleSearchVo
 * @Description 返回给前端的search结果 包含文章 id 和 title
 * @Author AoLinChen
 */
@Data
public class ArticleSearchVo {

    private Long id;

    private String title;
}
