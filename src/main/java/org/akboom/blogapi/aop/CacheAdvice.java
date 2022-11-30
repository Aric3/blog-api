package org.akboom.blogapi.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.akboom.blogapi.vo.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @Classname CacheAdvice
 * @Description Cache通知处理类
 * @Author AoLinChen
 */
@Aspect
@Slf4j
@Component
public class CacheAdvice {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Around("@annotation(org.akboom.blogapi.aop.Cache)")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            Signature signature = pjp.getSignature();
            //类名
            String className = pjp.getTarget().getClass().getSimpleName();
            //调用的方法名
            String methodName = signature.getName();

            Class[] parameterTypes = new Class[pjp.getArgs().length];
            Object[] args = pjp.getArgs();
            //参数
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    params.append(JSON.toJSONString(args[i]));
                    parameterTypes[i] = args[i].getClass();
                } else {
                    parameterTypes[i] = null;
                }
            }
            if (StringUtils.isNotEmpty(params.toString())) {
                //加密 以防出现key过长以及字符转义获取不到的情况
                params = new StringBuilder(DigestUtils.md5Hex(params.toString()));
            }
            Method method = pjp.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);
            //获取Cache注解
            Cache annotation = method.getAnnotation(Cache.class);
            //缓存过期时间
            long expire = annotation.expire();
            //缓存名称
            String name = annotation.name();

            //先从redis获取
            String redisKey = name + "::" + className + "::" + methodName + "::" + params;
            String redisValue = redisTemplate.opsForValue().get(redisKey);
            if (StringUtils.isNotEmpty(redisValue)) {
                log.info("走了缓存~~~,{},{}", className, methodName);
                return JSON.parseObject(redisValue, Result.class);
            }
            //redis不存在 执行方法
            Object result = pjp.proceed();
            redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(result), Duration.ofMillis(expire));
            log.info("存入缓存~~~ {},{}", className, methodName);
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return Result.fail(-999, "系统错误");
    }
}
