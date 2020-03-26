package com.qinyue.monitor.util;

import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.login.UserBean;
import com.xuexiang.xutil.data.SPUtils;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/26
 * 描述:
 **/
public class UserUtils {
    public static boolean isLogin(){
        UserBean object = SPUtils.getObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, UserBean.class);
        if (object!=null){
            return true;
        }else {
            return false;
        }
    }
    public static String getRealName(){
        UserBean object = SPUtils.getObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, UserBean.class);
        if (object!=null){
            return object.getRealName();
        }else {
            return "";
        }
    }
    public static String getUserName(){
        UserBean object = SPUtils.getObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, UserBean.class);
        if (object!=null){
            return object.getUsername();
        }else {
            return "";
        }
    }
}
