package org.akboom.blogapi.service.impl;

import org.akboom.blogapi.dao.dos.Archive;
import org.akboom.blogapi.dao.mapper.ArticleMapper;
import org.akboom.blogapi.service.ArticleService;
import org.akboom.blogapi.vo.ArticleVo;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * @param pageParam
     * @return
     * @description 分页查询文章列表
     */
    @Override
    public Result getArticleList(PageParam pageParam) {

        int offset = (pageParam.getPage() - 1) * pageParam.getPageSize();
        int pageSize = pageParam.getPageSize();
        List<ArticleVo> articleVoList = articleMapper.selectArticleByPageParam(offset, pageSize);
        return Result.success(articleVoList);
    }

    /**
     * @param
     * @return Result
     * @Description 查询view_counts最多的limit个文章
     */
    @Override

    public Result getHotArticle() {
        int limit = 6;
        List<HashMap<String, Object>> data = articleMapper.selectHotArticle(limit);
        return Result.success(data);
    }


    @Override
    public Result getNewArticle() {
        int limit = 6;
        List<HashMap<String, Object>> data = articleMapper.selectNewArticle(limit);
        return Result.success(data);
    }
    /**
     * @Description 查询归档数据
     * @param
     * @return Result
     */
    @Override
    public Result getArchives() {
        List<Archive> archives = articleMapper.selectArchive();
        return  Result.success(archives);
    }


}
