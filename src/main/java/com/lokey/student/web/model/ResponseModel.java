package com.lokey.student.web.model;

/**
 * Created by yinhaijin on 15/12/4.
 */
public class ResponseModel {

    private int code;

    private String Message;

    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
