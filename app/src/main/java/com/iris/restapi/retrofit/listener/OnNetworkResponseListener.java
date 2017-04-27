package com.iris.restapi.retrofit.listener;

import com.iris.restapi.retrofit.vo.ErrorVO;

/**
 * Created by shoki on 2017. 4. 26..
 */

public abstract class OnNetworkResponseListener<ITEM> {

    public abstract void success(ITEM item);
    public abstract void error(ErrorVO error);

    public void onFailed(ErrorVO errorVO){

    }
}
