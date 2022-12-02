package org.akboom.blogapi.service.impl;

import org.akboom.blogapi.dao.mapper.ArticleMapper;
import org.akboom.blogapi.dao.mapper.CommentMapper;
import org.akboom.blogapi.dao.pojo.Comment;
import org.akboom.blogapi.dao.pojo.SysUser;
import org.akboom.blogapi.service.CommentService;
import org.akboom.blogapi.util.SnowFlakeUtils;
import org.akboom.blogapi.util.UserThreadLocal;
import org.akboom.blogapi.vo.CommentVo;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname CommentServiceImpl
 * @Description 评论业务逻辑类
 * @Author AoLinChen
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Result getCommentsByArticleId(long articleId) {
        List<CommentVo> comments = commentMapper.selectCommentsByArticleId(articleId);
        return Result.success(comments);
    }

    @Override
    public Result createComment(CommentParam commentParam) {

        //获取用户信息
        SysUser sysUser = UserThreadLocal.get();
        //创建评论对象
        Comment comment = new Comment();
        //设置评论的默认参数
        //雪花算法生成id
        long id = SnowFlakeUtils.nextId();
        comment.setId(id);
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        } else {
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        this.commentMapper.insertComment(comment);

        /*文章的评论数+1 且保证数据一致性*/
        Long articleId = commentParam.getArticleId();
        int oldCounts = articleMapper.selectCommentCountsById(articleId);
        int newCounts = oldCounts + 1;
        articleMapper.updateCommentCountsById(newCounts, oldCounts, articleId);

        /*将建立的评论返回给前端 实现评论的实时刷新*/
        return getCommentById(id);

    }

    @Override
    public Result getCommentById(Long id) {
        CommentVo comment = commentMapper.selectCommentById(id);
        return Result.success(comment);
    }
}
