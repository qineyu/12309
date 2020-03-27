package com.qinyue.monitor.base;

import java.io.Serializable;

/**
 * 基本数据格式化base类
 * @param <T>
 */
public class BaseBean2<T> implements Serializable {
    private int code = -1;
    private T res = null;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public T getData() {
        return res;
    }

    public void setData(T data) {
        this.res = data;
    }
}
