package com.iris.restapi.retrofit.vo;

/**
 * Created by Ted
 */
public class ErrorVO {


    //eror tpye 마들기
    private String code;
    private String status;
    private String message;

    public ErrorVO() {}

    public ErrorVO(String code) {
        this.code = code;
        status = null;
        message = null;
    }

    public String getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
