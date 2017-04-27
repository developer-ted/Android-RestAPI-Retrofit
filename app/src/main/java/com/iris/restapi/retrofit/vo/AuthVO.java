package com.iris.restapi.retrofit.vo;

/**
 * Created by Ted
 */
public class AuthVO {

    private Auth auth;
    private UserInfoVO userInfo;

    public Auth getAuth() {
        return auth;
    }

    public UserInfoVO getUserInfo() {
        return userInfo;
    }

    public class Auth {
        private AuthTokenVO token;

        public AuthTokenVO getToken() {
            return token;
        }
    }
}
