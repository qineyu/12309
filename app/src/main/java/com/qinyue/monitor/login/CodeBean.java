package com.qinyue.monitor.login;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/27
 * 描述:
 **/
public class CodeBean implements Serializable {
    private String message;
    private boolean result;
    private int validTime;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getValidTime() {
        return validTime;
    }

    public void setValidTime(int validTime) {
        this.validTime = validTime;
    }
}
