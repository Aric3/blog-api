package org.akboom.blogapi.service;

import org.akboom.blogapi.vo.Result;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname TagService
 * @Description 标签业务逻辑接口
 * @Author AoLinChen
 */
@Transactional
public interface TagService {

    /**
     * @return Result
     */
    Result getHotTags();

    Result getTags();

    Result getTagDetails();

    Result getTagDetailById(Long id);
}
