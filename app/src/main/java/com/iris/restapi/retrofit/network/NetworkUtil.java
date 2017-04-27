package com.iris.restapi.retrofit.network;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.iris.restapi.retrofit.vo.ErrorVO;
import com.iris.restapi.util.Utils;

import retrofit2.Response;

/**
 * Created by shoki on 2017. 4. 27..
 */

public class NetworkUtil {

    public static ErrorVO parseError(Response response) {
        ErrorVO error = new ErrorVO();
        if(response.errorBody() != null && response.errorBody().byteStream() != null) {
            try {
                error = new Gson().fromJson(Utils.toString(response.errorBody().byteStream()), ErrorVO.class);
            } catch (JsonSyntaxException e) {
                return error;
            } catch (NullPointerException e) {
                return error;
            }
        } else {
            return error;
        }
        return error;
    }

    public static ErrorVO parseErrorWithOkHttp(okhttp3.Response response) {
        ErrorVO error;
        if (response.body() != null && response.body().byteStream() != null) {
            try {
                error = new Gson().fromJson(Utils.toString(response.body().byteStream()), ErrorVO.class);
            } catch (JsonSyntaxException e) {
                return null;
            } catch (NullPointerException e) {
                return null;
            }
        } else {
            return null;
        }
        return error;
    }

}
