package com.iris.restapi.retrofit.repo;

import android.content.Context;

import com.iris.restapi.retrofit.APIUtils;
import com.iris.restapi.retrofit.NetworkManager;
import com.iris.restapi.retrofit.config.APIConfig;
import com.iris.restapi.retrofit.listener.MADCallback;
import com.iris.restapi.retrofit.listener.OnNetworkResponseListener;
import com.iris.restapi.retrofit.network.ClientFactory;
import com.iris.restapi.retrofit.services.AuthServices;
import com.iris.restapi.retrofit.vo.VerificationVO;

import retrofit2.Call;

/**
 * Created by shoki on 2017. 4. 26..
 */

public class AuthRepo <ITEM> {

    private Context context;
    private Call call;

    public AuthRepo(Context context) {
        this.context = context;
    }

    private OnNetworkResponseListener<ITEM> listener;
    public AuthRepo setOnNetworkResponseListener(OnNetworkResponseListener<ITEM> listener) {
        this.listener = listener;
        return this;
    }

    public AuthRepo requestLogin() {
        final AuthServices service = ClientFactory.createBase(context, AuthServices.class);
        call = service.requestAuthCharis(APIConfig.PROVIDER_CHARIS, new VerificationVO("","", APIUtils.getSID(context)));
        return this;
    }

    public void build() {
        NetworkManager.getInstance().add(call, new MADCallback(context, listener));
    }
}
