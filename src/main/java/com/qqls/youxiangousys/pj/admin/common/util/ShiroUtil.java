package com.qqls.youxiangousys.pj.admin.common.util;

import com.qqls.youxiangousys.pj.admin.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    //获取登录的用户
    public static SysUser getLoginUser(){
        //SecurityUtils.getSubject():获取Subject实现对象
        //Subject:管理主体对象即登录对象
        //Subject().getPrincipal();:获取登录对象
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    //获取登录用户名
    public static String getUsername(){
        return getLoginUser().getUsername();
    }
}
