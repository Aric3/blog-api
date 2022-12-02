package org.akboom.blogapi.service;

import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.CommentParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname CommentService
 * @Description 评论业务逻辑接口
 * @Author AoLinChen
 */
@Transactional
public interface CommentService {
    Result getCommentsByArticleId(long articleId);

    Result createComment(CommentParam commentParam);

    Result getCommentById(Long id);
}
