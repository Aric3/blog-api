package org.akboom.blogapi.controller;

import org.akboom.blogapi.service.CommentService;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.CommentParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname CommentController
 * @Description 处理评论相关的请求
 * @Author AoLinChen
 */
@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("article/{id}")
    public Result getCommentsByArticleId(@PathVariable long id) {
       return commentService.getCommentsByArticleId(id);
    }

    @PostMapping("create/change")
    public Result createComment(@RequestBody CommentParam commentParam){
        return commentService.createComment(commentParam);
    }

}
