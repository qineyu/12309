package com.qinyue.monitor.base;

import java.io.Serializable;

/**
 * 基本数据格式化base类
 * @param <T>
 */
public class BaseBean<T> implements Serializable {
    private int code = -1;
    private String msg = "";
    private T result = null;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return result;
    }

    public void setData(T data) {
        this.result = data;
    }
}
