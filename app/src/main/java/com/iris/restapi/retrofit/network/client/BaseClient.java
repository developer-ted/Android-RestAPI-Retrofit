package com.iris.restapi.retrofit.network.client;

import android.content.Context;

import com.iris.restapi.retrofit.Client;
import com.iris.restapi.retrofit.network.interceptor.HeaderFactory;
import com.iris.restapi.retrofit.network.interceptor.OauthInterceptor;

import retrofit2.Retrofit;

/**
 * Created by shoki on 2017. 4. 27..
 */

public class BaseClient {
    private static Retrofit retrofit = null;
    private BaseClient() {

    }

    public static Retrofit getInstance(Context context) {
        if(retrofit ==  null) {
            retrofit = new Client("")
                    .addInterceptor(HeaderFactory.create(context, HeaderFactory.HeaderType.COMMON))
                    .addInterceptor(OauthInterceptor.create(context))
                    .build();
        }

        return retrofit;
    }
}
