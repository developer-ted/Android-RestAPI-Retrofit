package com.iris.restapi.retrofit.services;

import com.iris.restapi.retrofit.vo.MainResultVO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Ted
 * Modified by Adel 2016. 9. 5
 */
public interface MainServices {

    /**
     * Adel, 2016-09-05
     * 모바일 메인
     *
     * @param lang 언어코드
     * @return call object
     */
    @GET("/mobile/tryme")
    Call<MainResultVO> requestMainList(@Query("lang") String lang);
}
