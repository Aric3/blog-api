package org.akboom.blogapi.service.impl;

import org.akboom.blogapi.dao.mapper.SysUserMapper;
import org.akboom.blogapi.dao.pojo.SysUser;
import org.akboom.blogapi.service.LoginService;
import org.akboom.blogapi.service.SysUserService;
import org.akboom.blogapi.vo.ErrorCode;
import org.akboom.blogapi.vo.LoginUserVo;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.LoginParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;


/**
 * @Classname SysUserServiceImpl
 * @Description 用户业务逻辑
 * @Author AoLinChen
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    @Lazy
    private LoginService loginService;

    /**
     * @param loginParam
     * @return sysuser
     * @Description 根据account和password查找系统用户
     */
    public SysUser getUserByAccountAndPasswd(LoginParam loginParam) {
        return sysUserMapper.getUserByAccountAndPasswd(loginParam);
    }

    /**
     * @param account
     * @return salt
     * @Description 根据account获取对应用户的加密盐
     */
    public String getUserSaltByAccount(String account) {
        return sysUserMapper.getUserSaltByAccount(account);
    }

    @Override
    public Result getUserByToken(String token) {
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null) {
            return Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        BeanUtils.copyProperties(sysUser, loginUserVo);
        return Result.success(loginUserVo);
    }

    @Override
    public SysUser getUserByAccount(String account) {
        return sysUserMapper.selectUserByAccount(account);
    }

    @Override
    public int saveUser(SysUser user) {
        return sysUserMapper.insertUser(user);
    }

}
