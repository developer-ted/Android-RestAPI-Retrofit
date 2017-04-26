package com.iris.restapi.retrofit;

import android.text.TextUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shoki on 2017. 4. 26..
 */

public class Client {

    private static boolean DEBUG = false;

    private Retrofit.Builder retrofit;
    private OkHttpClient.Builder builder = new OkHttpClient.Builder();
    private String baseURL = "";
    private HashMap<String, Object> header = new HashMap<>();

    public static void setDebugMode(boolean debug) {
        DEBUG = debug;
    }

    public Client(String baseURL) {
        if(TextUtils.isEmpty(baseURL)) {
            throw new RuntimeException("base url empty !");
        }
        this.baseURL = baseURL;
        this.retrofit = new Retrofit.Builder();
    }

    public Client addInterceptor(Interceptor interceptor) {
        builder.addInterceptor(interceptor);
        return this;
    }

    public <V> Client addHeader(String key, V value) {
        header.put(key, value);
        return this;
    }

    public <V> Client addHeaders(HashMap<String, V> headers) {
        header = (HashMap<String, Object>) headers;
        return this;
    }

    public Retrofit build() {
        if(DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        if(!header.isEmpty()) {
            builder.addInterceptor(chain -> chain.proceed(ClientHeader.createHeader(chain, header)));
        }

        return retrofit
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();
    }
}
