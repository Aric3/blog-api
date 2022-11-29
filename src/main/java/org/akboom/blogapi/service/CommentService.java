package org.akboom.blogapi.service;

import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.CommentParam;

/**
 * @Classname CommentService
 * @Description 评论业务逻辑接口
 * @Author AoLinChen
 */
public interface CommentService {
    Result getCommentsByArticleId(long articleId);

    Result createComment(CommentParam commentParam);
}
