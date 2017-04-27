package com.iris.restapi.retrofit.listener;

import android.content.ComponentName;
import android.content.Context;

import com.iris.restapi.retrofit.network.NetworkUtil;
import com.iris.restapi.retrofit.vo.ErrorVO;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by shoki on 2017. 4. 27..
 */

public class MADCallback<ITEM> implements retrofit2.Callback<ITEM> {

    private Context context;
    private OnNetworkResponseListener<ITEM> networkResponseListener;

    public MADCallback(Context context, OnNetworkResponseListener<ITEM> networkResponseListener) {
        this.networkResponseListener = networkResponseListener;
        this.context = context;
    }

    @Override
    public void onResponse(Call<ITEM> call, Response<ITEM> response) {
        if(response.isSuccessful()) {
            if(networkResponseListener != null) {
                networkResponseListener.success(response.body());
            }
        } else {
            if(networkResponseListener != null) {
                networkResponseListener.error(NetworkUtil.parseError(response));
            }
        }
    }

    @Override
    public void onFailure(Call<ITEM> call, Throwable t) {
        //stop loading progressbar
        ErrorVO errorVO = new ErrorVO();
        errorVO.setMessage(t.getMessage());

        if(networkResponseListener != null) {
            networkResponseListener.error(errorVO);
        }
    }
}
