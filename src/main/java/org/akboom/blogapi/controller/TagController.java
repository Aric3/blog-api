package org.akboom.blogapi.controller;

import org.akboom.blogapi.aop.Logging;
import org.akboom.blogapi.service.TagService;
import org.akboom.blogapi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TagController
 * @Description 处理标签相关请求
 * @Author AoLinChen
 */
@RestController()
@RequestMapping("tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("hot")
    public Result getHotTag() {
        return tagService.getHotTags();
    }
    @GetMapping
    @Logging(module = "Tag",operation = "getTags()")
    public Result getTags(){return tagService.getTags();}
}
