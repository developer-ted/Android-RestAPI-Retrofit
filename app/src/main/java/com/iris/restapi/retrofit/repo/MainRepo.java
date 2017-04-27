package com.iris.restapi.retrofit.repo;

import android.content.Context;

import com.iris.restapi.retrofit.APIUtils;
import com.iris.restapi.retrofit.NetworkManager;
import com.iris.restapi.retrofit.config.APIConfig;
import com.iris.restapi.retrofit.listener.MADCallback;
import com.iris.restapi.retrofit.listener.OnNetworkResponseListener;
import com.iris.restapi.retrofit.network.ClientFactory;
import com.iris.restapi.retrofit.services.AuthServices;
import com.iris.restapi.retrofit.services.MainServices;
import com.iris.restapi.retrofit.vo.VerificationVO;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by shoki on 2017. 4. 27..
 */

public class MainRepo<ITEM> {

    private Context context;
    private Call call;

    public MainRepo(Context context) {
        this.context = context;
    }

    private OnNetworkResponseListener<ITEM> listener;
    public MainRepo setOnNetworkResponseListener(OnNetworkResponseListener<ITEM> listener) {
        this.listener = listener;
        return this;
    }

    public MainRepo requestMainList() {
        Retrofit retrofit = ClientFactory.create(context);
        MainServices service = retrofit.create(MainServices.class);
        call = service.requestMainList("en");
        return this;
    }

    public void build() {
        NetworkManager.getInstance().add(call, new MADCallback(context, listener));
    }

}
