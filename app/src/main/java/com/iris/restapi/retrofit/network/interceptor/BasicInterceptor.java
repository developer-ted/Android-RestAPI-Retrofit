package com.iris.restapi.retrofit.network.interceptor;

import android.content.Context;

import com.iris.restapi.retrofit.APIUtils;
import com.iris.restapi.retrofit.TokenManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.iris.restapi.retrofit.network.Config.*;

/**
 * Created by shoki on 2017. 4. 27..
 */

public class BasicInterceptor implements Interceptor {

    private Context context;

    public BasicInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.header(AUTHORIZATION, BASIC + TokenManager.getBasicToken(context));
        builder.header(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON);
        builder.header(CACHE_CONTROL, CACHE_NO_CACHE);
        builder.header(APP_VERSION, String.valueOf(APIUtils.getVersionCode(context, context.getPackageName())));
        builder.header(TZ_OFFSET, APIUtils.getCurrentTimezoneOffset());
        builder.header(COUNTRY, APIUtils.getDeviceCountryCode());
        builder.header(ACCEPT_LANGUAGE, "en");
        builder.header(PLATFORM, PLATFORM_ANDROID);

        return chain.proceed(builder.build());
    }
}
