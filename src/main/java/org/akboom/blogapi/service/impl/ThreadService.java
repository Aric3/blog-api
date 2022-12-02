package org.akboom.blogapi.service.impl;

import org.akboom.blogapi.dao.mapper.ArticleMapper;
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
        /*TODO:使用redis和mysql数据同步保证阅读数正常*/
        int viewCountsOld = viewCounts;
        int viewCountsNew = viewCounts + 1;
        //传入未自增前的viewCounts保证一致性
        articleMapper.updateViewCountsById(viewCountsNew, viewCountsOld,articleId);
    }
}
