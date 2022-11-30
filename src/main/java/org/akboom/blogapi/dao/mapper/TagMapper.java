package org.akboom.blogapi.dao.mapper;

import org.akboom.blogapi.dao.pojo.Tag;
import org.akboom.blogapi.vo.TagVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TagMapper {

  /**
   * @Description 查找文章数最多的limit个标签
   * @param limit
   * @return id列表
   */
    List<Long> selectHotTagIds(@Param("limit")int limit);
    /**
     * @Description 根据传入id查找标签数组
     * @param ids
     * @return Tag的数组
     */
    List<Tag> selectTagsByIds(@Param("ids") List<Long> ids);

    List<TagVo> selectTags();

    List<Tag> selectTagDetails();

    Tag selectTagDetailById(@Param("id") Long id);
}
