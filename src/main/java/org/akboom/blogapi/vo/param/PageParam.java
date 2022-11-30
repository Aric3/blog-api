package org.akboom.blogapi.vo.param;

import lombok.Data;

/**
 * @Classname PageParam
 * @Description 分页请求 数据对象
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

    /*
    * 目录号
    */
    private Long categoryId;
    /*
    * 标签号
    */
    private Long tagId;

    private String year;

    private String month;

    public String getMonth(){
        if (this.month != null && this.month.length() == 1){
            return "0"+this.month;
        }
        return this.month;
    }
}
