package com.iris.restapi.retrofit;

import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shoki on 2017. 4. 26..
 */

public class ClientHeader {

    public static <V> Request createHeader(Interceptor.Chain chain, HashMap<String, V> headers) {
        Request.Builder builder = chain.request().newBuilder();
        for(String key : headers.keySet()) {
            builder.header(key, String.valueOf(headers.get(key)));
        }
        return builder.build();
    }
}
