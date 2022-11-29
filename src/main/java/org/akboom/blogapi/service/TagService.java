package org.akboom.blogapi.service;

import org.akboom.blogapi.vo.Result;

/**
 * @Classname TagService
 * @Description 标签业务逻辑接口
 * @Author AoLinChen
 */
public interface TagService {

    /**
     * @return Result
     */
    Result getHotTags();

    Result getTags();
}
