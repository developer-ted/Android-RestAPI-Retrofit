package com.iris.restapi.retrofit.vo;

/**
 * Created by Ted
 */
public class CheckUserVO {

    public static final int TYPE_USER_EMAIL = 0;
    public static final int TYPE_USER_NICKNAME = 1;
    public static final int TYPE_USER_URL = 2;

    private String email;
    private String nickname;
    private String url;
    private String passwd;
    private String new_passwd;

    public CheckUserVO(int type, String object) {
        if (type == TYPE_USER_EMAIL) email = object;
        else email = null;

        if (type == TYPE_USER_NICKNAME) nickname = object;
        else nickname = null;

        if (type == TYPE_USER_URL) url = object;
        else url = null;
    }

    public CheckUserVO(String passwd, String new_passwd) {
        email = null;
        nickname = null;
        url = null;
        this.passwd = passwd;
        this.new_passwd = new_passwd;
    }
}
