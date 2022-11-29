package org.akboom.blogapi.dao.mapper;

import org.akboom.blogapi.dao.pojo.SysUser;
import org.akboom.blogapi.vo.param.LoginParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper {
    /**
     * @param id
     * @return SysUser
     * @Description 根据用户id查询用户对象
     */
    SysUser getUserById(@Param("id") Long id);

    /**
     * @param account
     * @return salt
     * @Description 根据用户id查询对应加密盐
     */
    String getUserSaltByAccount(@Param("account") String account);
    /**
     * @Description 根据用户名和密码查询用户
     * @param loginParam
     * @return sysuser
     */

    SysUser getUserByAccountAndPasswd(LoginParam loginParam);

    SysUser selectUserByAccount(String account);

    int insertUser(SysUser user);
}
