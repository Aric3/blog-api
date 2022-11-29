package org.akboom.blogapi.service.impl;

import org.akboom.blogapi.dao.mapper.TagMapper;
import org.akboom.blogapi.dao.pojo.Tag;
import org.akboom.blogapi.service.TagService;
import org.akboom.blogapi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname TagServiceImpl
 * @Description 标签业务逻辑
 * @Author AoLinChen
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * @param
     * @return Result
     * @Description 获取最热标签
     */
    @Override
    public Result getHotTags() {

        int limit = 6;
        List<Long> ids = tagMapper.selectHotTagIds(limit);
        List<Tag> tags = tagMapper.selectTagsByIds(ids);
        return Result.success(tags);
    }
}
