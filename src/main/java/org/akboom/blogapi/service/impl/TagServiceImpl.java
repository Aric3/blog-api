package org.akboom.blogapi.service.impl;

import org.akboom.blogapi.dao.mapper.TagMapper;
import org.akboom.blogapi.dao.pojo.Tag;
import org.akboom.blogapi.service.TagService;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.TagVo;
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
        if (ids.size() != 0) {
            List<Tag> tags = tagMapper.selectTagsByIds(ids);
            return Result.success(tags);
        }else return Result.success(null);

    }

    @Override
    public Result getTags() {
        List<TagVo> tags = tagMapper.selectTags();
        return Result.success(tags);
    }

    @Override
    public Result getTagDetails() {
        List<Tag> tags = tagMapper.selectTagDetails();
        return Result.success(tags);
    }

    @Override
    public Result getTagDetailById(Long id) {
        Tag tag = tagMapper.selectTagDetailById(id);
        return Result.success(tag);
    }
}
