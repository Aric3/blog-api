package org.akboom.blogapi.service.impl;

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
        //使用雪花算法生成id
        long id = SnowFlakeUtils.nextId();
        comment.setId(id);
        comment.setArticleId(commentParam.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParam.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        }else{
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        this.commentMapper.insertComment(comment);
        return Result.success(null);

    }
}
