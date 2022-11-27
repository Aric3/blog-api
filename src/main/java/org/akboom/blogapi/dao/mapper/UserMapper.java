package org.akboom.blogapi.dao.mapper;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    String selectTest();
}
