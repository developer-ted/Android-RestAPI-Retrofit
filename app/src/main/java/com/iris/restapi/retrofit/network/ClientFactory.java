package com.iris.restapi.retrofit.network;

import android.content.Context;

import com.iris.restapi.retrofit.Client;
import com.iris.restapi.retrofit.network.client.BaseClient;
import com.iris.restapi.retrofit.network.interceptor.HeaderFactory;
import com.iris.restapi.retrofit.network.interceptor.OauthInterceptor;

import retrofit2.Retrofit;

/**
 * Created by shoki on 2017. 4. 27..
 */

public class ClientFactory {

    public static <S> S createBase(Context context, Class service) {
        Retrofit retrofit = new Client("")
                .addInterceptor(HeaderFactory.create(context, HeaderFactory.HeaderType.BASIC))
                .addInterceptor(new OauthInterceptor(context))
                .build();

        return (S) retrofit.create(service);
    }

    public static <S> S create(Context context, Class service) {
        Retrofit retrofit = new Client("")
                .addInterceptor(HeaderFactory.create(context, HeaderFactory.HeaderType.COMMON))
                .addInterceptor(new OauthInterceptor(context))
                .build();

        return (S) retrofit.create(service);
    }

    public static Retrofit create(Context context) {
        return BaseClient.getInstance(context);
    }
}
