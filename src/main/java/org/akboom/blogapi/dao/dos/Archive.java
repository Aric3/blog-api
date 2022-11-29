package org.akboom.blogapi.dao.dos;

import lombok.Data;

/**
 * @Classname Archive
 * @Description 领域对象 归档
 * @Author AoLinChen
 */
@Data
public class Archive {

    private String year;

    private String month;

    private int count;

}
