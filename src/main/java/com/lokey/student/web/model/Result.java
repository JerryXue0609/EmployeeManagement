package com.lokey.student.web.model;

/**
 * Created by Lokey on 2016/8/15.
 */
public class Result {
    private Object data;
    private Integer code;
    private String msg;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
