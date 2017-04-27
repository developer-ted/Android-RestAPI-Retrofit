package com.iris.restapi.retrofit.network.interceptor;

import android.content.Context;
import android.util.Log;

import com.iris.restapi.retrofit.APIUtils;
import com.iris.restapi.retrofit.NetworkManager;
import com.iris.restapi.retrofit.TokenManager;
import com.iris.restapi.retrofit.network.ClientFactory;
import com.iris.restapi.retrofit.network.ErrorType;
import com.iris.restapi.retrofit.network.NetworkUtil;
import com.iris.restapi.retrofit.services.AuthServices;
import com.iris.restapi.retrofit.vo.AuthVO;
import com.iris.restapi.retrofit.vo.ErrorVO;
import com.iris.restapi.retrofit.vo.VerificationVO;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shoki on 2017. 4. 27..
 */

public class OauthInterceptor implements Interceptor {

    private final String TAG = getClass().getSimpleName();
    private Context context;

    private static boolean isRetryInvalidAccessToken = false;

    public OauthInterceptor(Context context) {
        this.context = context;
    }

    public static Interceptor create(Context context) {
        return new OauthInterceptor(context);
    }

    private boolean isNotExistsToken(String code) {
        switch (code) {
            case ErrorType.AUTH.EXPIRED_TOKEN:
            case ErrorType.COMMON.EXPIRED_TOKEN:
            case ErrorType.COMMON.INVALID_TOKEN:
                //토큰만료
            return true;
        }
        return false;
    }

    private boolean isExpiredToken(String code) {
        switch (code) {
            case ErrorType.COMMON.NOT_EXISTS_TOKEN:
                //토큰갱신
                return true;
        }
        return false;
    }



//    @Override
//    public Request authenticate(Route route, okhttp3.Response response) throws IOException {
//        if (response.code() == 401 || response.code() == 403) {
//            ErrorVO error = NetworkUtil.parseErrorWithOkHttp(response);
//            if (error != null && isNotExistsToken(error.getCode())) {
//                String refresh = TokenManager.getRefreshToken(context);
//                String sid = APIUtils.getSID(context);
//
//                final AuthServices service = ClientFactory.createBase(context, AuthServices.class);
//                Call<AuthVO> call = service.requestAuthRefresh(new VerificationVO(true, refresh, sid));
//                try {
//                    Response<AuthVO> tokenResponse = call.execute();
//                    Log.d(TAG, "authenticate: token refresh");
//                    if (tokenResponse.code() == 200) {
//                        AuthVO authVO = tokenResponse.body();
//                        TokenManager.setToken(context, authVO.getAuth().getToken());
//                        return response.request();
//                    } else {
//                        return null;
//                    }
//                } catch (IOException e) {
//                    return null;
//                }
//            } else if (error != null && isExpiredToken(error.getCode())) {
//                //토큰만료시 재요청
//                if(!isRetryInvalidAccessToken) {
//                    Log.d(TAG, "authenticate: token expired and retry call " + isRetryInvalidAccessToken);
//                    isRetryInvalidAccessToken = true;
//                    return response.request();
//                //토큰만료시 리셋하고 로그인페이지로 이동
//                } else {
//                    Log.d(TAG, "authenticate: token reset // " + isRetryInvalidAccessToken);
//                    isRetryInvalidAccessToken = false;
//                    TokenManager.resetToken(context);
//                    NetworkManager.getInstance().clearQueue();
//                }
//                return null;
//            } else {
//                return null;
//            }
//        }
//
//        return null;
//    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Response response = chain.proceed(chain.request());
        if (response.code() == 401 || response.code() == 403) {
            ErrorVO error = NetworkUtil.parseErrorWithOkHttp(response);
            if (error != null && isNotExistsToken(error.getCode())) {
                String refresh = TokenManager.getRefreshToken(context);
                String sid = APIUtils.getSID(context);

                final AuthServices service = ClientFactory.createBase(context, AuthServices.class);
                Call<AuthVO> call = service.requestAuthRefresh(new VerificationVO(true, refresh, sid));
                try {
                    Response<AuthVO> tokenResponse = call.execute();
                    Log.d(TAG, "authenticate: token refresh");
                    if (tokenResponse.code() == 200) {
                        AuthVO authVO = tokenResponse.body();
                        TokenManager.setToken(context, authVO.getAuth().getToken());
                        return chain.proceed(chain.request().newBuilder().build());
                    } else {
                        return response;
                    }
                } catch (IOException e) {
                    return response;
                }
            } else if (error != null && isExpiredToken(error.getCode())) {
                //토큰만료시 재요청
                if(!isRetryInvalidAccessToken) {
                    Log.d(TAG, "authenticate: token expired and retry call " + isRetryInvalidAccessToken);
                    isRetryInvalidAccessToken = true;
                    return response;
                    //토큰만료시 리셋하고 로그인페이지로 이동
                } else {
                    Log.d(TAG, "authenticate: token reset // " + isRetryInvalidAccessToken);
                    isRetryInvalidAccessToken = false;
                    TokenManager.resetToken(context);
                    NetworkManager.getInstance().clearQueue();
                }
                return response;
            } else {
                return response;
            }
        }

        return response;
    }
}
