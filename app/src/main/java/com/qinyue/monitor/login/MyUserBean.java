package com.qinyue.monitor.login;

import java.io.Serializable;
import java.util.List;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/27
 * 描述:
 **/
public class MyUserBean implements Serializable {
    private String sessionCode;
    private BaseUserBean baseUser;
    private String type;
    private List<String> userType;

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public BaseUserBean getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(BaseUserBean baseUser) {
        this.baseUser = baseUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getUserType() {
        return userType;
    }

    public void setUserType(List<String> userType) {
        this.userType = userType;
    }
}
