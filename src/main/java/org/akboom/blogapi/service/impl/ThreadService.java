package org.akboom.blogapi.service.impl;

import org.akboom.blogapi.dao.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Classname ThreadService
 * @Description 处理多线程任务
 * @Author AoLinChen
 */
@Component
public class ThreadService {

    @Async
    public void updateArticleViewCounts(ArticleMapper articleMapper, Long articleId, int viewCounts) {
        /*TODO:使用redis原子操作和定时任务框架实现修改的一致性*/
        int viewCountsOld = viewCounts;
        int viewCountsNew = viewCounts + 1;
        //传入未自增前的viewCounts保证一致性
        articleMapper.updateViewCountsById(viewCountsNew, viewCountsOld,articleId);
    }
}
