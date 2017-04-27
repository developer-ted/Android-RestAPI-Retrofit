package com.iris.restapi.retrofit.config;

/**
 * Class collection of API address when API Requests
 * Created by Ted
 */
public class APIAddress {

    //public static final String VERSION = "/v1";
    public static final String VERSION = "/v2";

    // Post
    public static final String POSTS = "/posts";
    public static final String NOTICE = "/notice";
    public static final String EVENT = "/event";

    // AUTH
    public static final String AUTH = "/auth";
    public static final String VERIFICATION = "/verification";
    public static final String REFRESH = "/refresh";
    public static final String USER_VERIFICATION_EMAIL = "/users/verification/email";
    public static final String USER_VERIFICATION_NICKNAME = "/users/verification/nickname";
    public static final String USER_VERIFICATION_URL = "/users/verification/url";
    public static final String PASSWORD_CHANGE = "/passwd/change";
    public static final String EMAIL_VERIFY = "/email/verify";

    // Reckoning
    public static final String RECKONINGS = "/reckonings";
    public static final String USERS = "/users";
    public static final String SNS = "/sns";
    public static final String SUMMARY = "/summary";

    // Main
    public static final String MAIN = "/main";
    public static final String BANNER = "/banner";

    // Product
    public static final String PRODUCTS = "/products";
    public static final String SAMPLE = "/sample";
    public static final String SALEGOODS_SG_ID = "/salegoods"+"/{" + APIParamValue.SG_ID + "}";
    public static final String SALEGOODS = "/salegoods";

    // config values
    public static final String CONFIG_VALUE = "/configvalues";
    public static final String MAIN_FOCUS_CELEB = "/main_focus_celeb";

    // address
    public static final String ADDRESS = "/address";
    public static final String COUNTRY = "/countries";
    public static final String EMS = "/deliveryprices/ems";
    public static final String DIALPOST = "/dialpost";
    public static final String DEFAULT = "/default";

    //Adel, 2016-09-05
    //Mobile
    public static final String MOBILE = "/mobile";
    public static final String MOBILE_HOME = "/tryme";

    //Sample
    public static final String SAMPLES = "/samples";

    //V2
    //public static final String V2 = "/v2";

    //withdraw
    public static final String WITHDRAW = "/withdraw";

    //image
    public static final String API = "/api";
    public static final String IMAGE = "/image";

    //splash
    public static final String SPLASH = "/mobile_android_version";

    //account
    public static final String ACCOUNT = "/accounts";

    //notification
    public static final String NOTIFICATION = "/notification";

    //alarm
    public static final String ALARM = "/alarms";

    // category
    public static final String V1_CATEGORIES = "/v1/categories";

    public static final String MARKET = "/market";

}
