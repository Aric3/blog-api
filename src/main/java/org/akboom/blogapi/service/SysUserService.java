package org.akboom.blogapi.service;

import org.akboom.blogapi.dao.pojo.SysUser;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.LoginParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname SysUserService
 * @Description 用户业务逻辑接口
 * @Author AoLinChen
 */
@Transactional
public interface SysUserService {
    SysUser getUserByAccountAndPasswd(LoginParam loginParam);
    String getUserSaltByAccount(String account);
    /**
     * @Description 通过解析token 从redis中提取sysuser对象
     * @param token
     * @return sysuser
     */

    Result getUserByToken(String token);

    SysUser getUserByAccount(String account);

    int saveUser(SysUser user);
}
