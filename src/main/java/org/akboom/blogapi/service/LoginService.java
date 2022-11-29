package org.akboom.blogapi.service;

import org.akboom.blogapi.dao.pojo.SysUser;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.LoginParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname LoginService
 * @Description 登录业务逻辑接口
 * @Author AoLinChen
 */
@Transactional
public interface LoginService {

    Result login(LoginParam loginParam);

    SysUser checkToken(String token);

    Result logout(String token);

    Result register(LoginParam loginParam);
}
