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
        Integer viewCountsOld = articleMapper.selectViewCountsById(articleId);
        if (viewCountsOld == viewCounts) {
            viewCounts += 1;
            articleMapper.updateViewCountsById(viewCounts, articleId);
        }

    }
}
