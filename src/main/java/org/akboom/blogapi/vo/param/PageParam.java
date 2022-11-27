package org.akboom.blogapi.vo.param;

import lombok.Data;

/**
 * @Classname PageParam
 * @Description 分页数据类
 * @Author AoLinChen
 */
@Data
public class PageParam {
    /**
     * 默认页号
     */
    private int page = 1;
    /**
     * 默认页大小
     */
    private int pageSize = 10;
}
