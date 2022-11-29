package org.akboom.blogapi.util;

import org.akboom.blogapi.dao.pojo.SysUser;

/**
 * @Classname UserThreadLocal
 * @Description ThreadLocal隔离变量
 * @Author AoLinChen
 */
public class UserThreadLocal {

    private UserThreadLocal (){}

    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }
    public static SysUser get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}
