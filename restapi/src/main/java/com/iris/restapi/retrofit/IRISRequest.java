package com.iris.restapi.retrofit;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by shoki on 2017. 4. 26..
 */

public class IRISRequest<ITEM> {
    Call<ITEM> call;
    Callback<ITEM> callback;

    public IRISRequest() {
    }

    public IRISRequest(Call<ITEM> call, Callback<ITEM> callback) {
        this.call = call;
        this.callback = callback;
    }

    public Call<ITEM> getCall() {
        return call;
    }

    public void setCall(Call<ITEM> call) {
        this.call = call;
    }

    public Callback<ITEM> getCallback() {
        return callback;
    }

    public void setCallback(Callback<ITEM> callback) {
        this.callback = callback;
    }
}
