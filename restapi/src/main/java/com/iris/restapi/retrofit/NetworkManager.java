package com.iris.restapi.retrofit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by shoki on 2017. 4. 26..
 */

public class NetworkManager {
    private static NetworkManager instance;
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private static List<IRISRequest> requestQueue = new ArrayList<>();


    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }

        return instance;
    }

    public NetworkManager() {
    }

    public synchronized void firstAdd(Call call, Callback callback) {
        IRISRequest request = new IRISRequest(call, callback);
        requestQueue.add(0, request);

        request();
    }

    public synchronized void add(Call call, Callback callback) {
        IRISRequest request = new IRISRequest(call, callback);
        requestQueue.add(request);

        request();
    }

    public AtomicBoolean getAtomicBoolean() {
        return atomicBoolean;
    }

    public List<IRISRequest> getRequestQueue() {
        return requestQueue;
    }

    public synchronized void lock() {
        atomicBoolean.set(true);
    }

    public synchronized void unLock() {
        atomicBoolean.set(false);
    }

    public synchronized boolean isLock() {
        return atomicBoolean.get();
    }

    public synchronized void unLockRequest() {
        unLock();
        request();
    }

    public void clearQueue() {
        unLock();
        getRequestQueue().clear();
    }


    public synchronized<T> void request() {
        if (isLock()) {
            return;
        }

        if (getRequestQueue().size() > 0) {
            lock();

            final IRISRequest request = getRequestQueue().get(0);
            getRequestQueue().remove(request);
            Call call = request.getCall().clone();
            final Callback callback = request.getCallback();

            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    callback.onResponse(call, response);
                    unLockRequest();
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    callback.onFailure(call, t);
                    unLockRequest();
                }
            });
        }
    }
}
