package com.qinyue.monitor.util;

import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.login.MyUserBean;
import com.qinyue.monitor.login.UserBean;
import com.xuexiang.xutil.data.SPUtils;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/26
 * 描述:
 **/
public class UserUtils {
    public static boolean isLogin(){
        MyUserBean object = SPUtils.getObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, MyUserBean.class);
        if (object!=null&&object.getBaseUser()!=null){
            return true;
        }else {
            return false;
        }
    }
    public static String getRealName(){
        MyUserBean object = SPUtils.getObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, MyUserBean.class);
        if (object!=null&&object.getBaseUser()!=null){
            return object.getBaseUser().getRealName();
        }else {
            return "";
        }
    }
    public static String getSex(){
        MyUserBean object = SPUtils.getObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, MyUserBean.class);
        if (object!=null&&object.getBaseUser()!=null){
            return "1".equals(object.getBaseUser().getGender())?"男":"女";
        }else {
            return "";
        }
    }
    public static String getUserName(){
        MyUserBean object = SPUtils.getObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, MyUserBean.class);
        if (object!=null&&object.getBaseUser()!=null){
            return object.getBaseUser().getUsername();
        }else {
            return "";
        }
    }
    public static String getIdCard(){
        MyUserBean object = SPUtils.getObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, MyUserBean.class);
        if (object!=null&&object.getBaseUser()!=null){
            return object.getBaseUser().getIdCard();
        }else {
            return "";
        }
    }
    public static String getEmail(){
        MyUserBean object = SPUtils.getObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, MyUserBean.class);
        if (object!=null&&object.getBaseUser()!=null){
            return object.getBaseUser().getEmail();
        }else {
            return "";
        }
    }
    public static String getAddress(){
        MyUserBean object = SPUtils.getObject(SPUtils.getDefaultSharedPreferences(), TagConstant.USERTAG, MyUserBean.class);
        if (object!=null&&object.getBaseUser()!=null){
            return object.getBaseUser().getAddr();
        }else {
            return "";
        }
    }
}
