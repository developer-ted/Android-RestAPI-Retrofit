package com.iris.restapi.retrofit.config;

/**
 * API Config
 * Created by Ted
 */
public class APIConfig {

    public static final int DEFAULT_LIMIT = 10;
    public static final int DEFAULT_MORE_LIMIT = 20;
    public static final int DEFAULT_PAGE = 1;

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    public static final String USER_ID = "user_id";
    public static final String USER_TYPE = "userType";

    public static final int USER_TYPE_SELLER = 1;
    public static final int USER_TYPE_CHILD_SELLER = 2;
    public static final int USER_TYPE_CUSTOMER = 3;

    public static final String TYPE_SELLER = "seller";
    public static final String TYPE_CUSTOMER = "customer";

    public static final String LANGUAGE_KO = "ko";
    public static final String LANGUAGE_EN = "en";
    public static final String LANGUAGE_CN = "CN";
    public static final String LANGUAGE_ZH_CN = "zh-CN";
    public static final String LANGUAGE_ZH_TW = "zh-TW";

    public static final String PROVIDER_GOOGLE = "google";
    public static final String PROVIDER_FACEBOOK = "facebook";
    public static final String PROVIDER_WEIBO = "weibo";
    public static final String PROVIDER_CHARIS= "charis";

    public static final int JOIN_PATH_EMAIL = 1;
    public static final int JOIN_PATH_FACEBOOK = 2;
    public static final int JOIN_PATH_GOOGLE = 3;
    public static final int JOIN_PATH_WEIBO = 4;
    public static final int JOIN_PATH_QQ = 5;

    public static final String ADDRESS_ID = "add_id";
    public static final String ALARM_ID = "al_id";

    public static final String DM_TYPE_EMAIL = "0";
    public static final String DM_TYPE_FACEBOOK = "1";
    public static final String DM_TYPE_INSTAGRAM = "2";
    public static final String DM_TYPE_WHATSAPP = "3";
    public static final String DM_TYPE_LINE = "4";
    public static final String DM_TYPE_WECHAT = "5";
    public static final String DM_TYPE_QQ = "6";
    public static final String DM_TYPE_KAKAOTALK = "7";
}
