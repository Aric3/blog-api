package org.akboom.blogapi.dao.mapper;

import org.akboom.blogapi.dao.pojo.Comment;
import org.akboom.blogapi.vo.CommentVo;
import org.akboom.blogapi.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname CommentMapper
 * @Description 评论相关mapper
 * @Author AoLinChen
 */
@Mapper
public interface CommentMapper {

    List<CommentVo> selectCommentsByArticleId(@Param("articleId")long articleId);

    UserVo selectUserVoByAuthorId(@Param("author_id")long id);

    int insertComment(Comment comment);

    CommentVo selectCommentById(Long id);
}
