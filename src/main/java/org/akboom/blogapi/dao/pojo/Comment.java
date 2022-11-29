package org.akboom.blogapi.dao.pojo;

import lombok.Data;

/**
 * @Classname Comment
 * @Description 评论持久化对象
 * @Author AoLinChen
 */
@Data
public class Comment {

    private Long id;

    private String content;

    private Long createDate;

    private Long articleId;

    private Long authorId;

    private Long parentId;

    private Long toUid;

    private Integer level;
}
