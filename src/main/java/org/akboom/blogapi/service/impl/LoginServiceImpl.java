package org.akboom.blogapi.service.impl;

import com.alibaba.fastjson.JSON;
import org.akboom.blogapi.dao.pojo.SysUser;
import org.akboom.blogapi.service.LoginService;
import org.akboom.blogapi.service.SysUserService;
import org.akboom.blogapi.util.JWTUtils;
import org.akboom.blogapi.util.SnowFlakeUtils;
import org.akboom.blogapi.vo.ErrorCode;
import org.akboom.blogapi.vo.Result;
import org.akboom.blogapi.vo.param.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Classname LoginServiceImpl
 * @Description 登录业务逻辑
 * @Author AoLinChen
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * @param loginParam
     * @return Result
     * @Description 登录逻辑判断 token操作
     */
    @Override
    public Result login(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        String salt = sysUserService.getUserSaltByAccount(account);
        String pwd = DigestUtils.md5Hex(password + salt);
        loginParam.setPassword(pwd);
        SysUser sysUser = sysUserService.getUserByAccountAndPasswd(loginParam);
        if (sysUser == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //登录成功，使用JWT生成token，返回token同时存放到redis中
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);

    }

    /**
     * @param token
     * @return sysuser
     * @Description 校验token是否有效 token为空 body为空 过期 均无效
     */

    @Override
    public SysUser checkToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Map<String, Object> body = JWTUtils.checkToken(token);
        if (body == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        return JSON.parseObject(userJson, SysUser.class);
    }

    /**
     * @param token
     * @return Result
     * @Description 退出
     */
    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }
    /**
     * @Description 注册逻辑
     * @param loginParam
     * @return Result
     */

    @Override
    public Result register(LoginParam loginParam) {
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(account)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)
        ) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = sysUserService.getUserByAccount(account);

        if (sysUser != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        //账户创建日期的md5值作为盐
        long dateTime = System.currentTimeMillis();
        String salt = DigestUtils.md5Hex(Long.toString(dateTime));

        sysUser = new SysUser();
        sysUser.setId(SnowFlakeUtils.nextId()); //雪花算法生成id
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+salt)); //密码md5加密
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setMobilePhoneNumber("");
        sysUser.setAdmin(false);  //1 为true
        sysUser.setDeleted(true); // 0 为false
        sysUser.setSalt(salt);
        sysUser.setStatus("");
        sysUser.setEmail("");

        sysUserService.saveUser(sysUser);
        //token
        String token = JWTUtils.createToken(sysUser.getId());

        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);

    }

}
