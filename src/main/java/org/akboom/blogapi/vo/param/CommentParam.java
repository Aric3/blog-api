package org.akboom.blogapi.vo.param;

import lombok.Data;

/**
 * @Classname CommentParam
 * @Description 前端传递的评论数据参数
 * @Author AoLinChen
 */
@Data
public class CommentParam {

    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;
}
