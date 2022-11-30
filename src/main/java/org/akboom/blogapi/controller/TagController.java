package org.akboom.blogapi.controller;

import org.akboom.blogapi.aop.Logging;
import org.akboom.blogapi.service.TagService;
import org.akboom.blogapi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result getTags(){return tagService.getTags();}

    @GetMapping("detail")
    public Result getAllTagDetails(){
        return tagService.getTagDetails();
    }

    @GetMapping("detail/{id}")
    public Result getTagDetailById(@PathVariable Long id){
        return tagService.getTagDetailById(id);
    }

}
