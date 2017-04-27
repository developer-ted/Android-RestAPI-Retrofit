package com.iris.restapi.retrofit.network.interceptor;

import android.content.Context;

import com.iris.restapi.retrofit.network.interceptor.BasicInterceptor;
import com.iris.restapi.retrofit.network.interceptor.CommonInterceptor;

import okhttp3.Interceptor;

/**
 * Created by shoki on 2017. 4. 27..
 */

public class HeaderFactory {

    public enum HeaderType {
        BASIC,
        COMMON,
        FILE
    }

    public static Interceptor create(Context context, HeaderType type) {
        if(type.equals(HeaderType.BASIC)) {
            return new BasicInterceptor(context);
        } else if(type.equals(HeaderType.FILE)) {

        } else {
            return new CommonInterceptor(context);
        }
        return new CommonInterceptor(context);
    }
}
