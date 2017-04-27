package com.iris.restapi.retrofit.services;

import com.iris.restapi.retrofit.vo.AuthVO;
import com.iris.restapi.retrofit.vo.CheckUserVO;
import com.iris.restapi.retrofit.vo.VerificationVO;
import com.iris.restapi.retrofit.config.APIAddress;
import com.iris.restapi.retrofit.config.APIParamValue;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by Ted
 */
public interface AuthServices {

    /**
     * Charis 를 사용한 로그인
     *
     * @param provider provider
     * @param vo       VerificationVO
     * @return call object
     */
    @POST(APIAddress.AUTH + "/{" + APIParamValue.PROVIDER + "}")
    Call<AuthVO> requestAuthCharis(@Path(APIParamValue.PROVIDER) String provider,
                                   @Body VerificationVO vo);

    /**
     * SNS 를 사용한 로그인
     *
     * @param provider sns provider
     * @param vo       VerificationVO
     * @return call object
     */
    @POST(APIAddress.AUTH + "/{" + APIParamValue.PROVIDER + "}" + APIAddress.VERIFICATION)
    Call<Object> requestAuthVerification(@Path(APIParamValue.PROVIDER) String provider,
                                         @Body VerificationVO vo);

    /**
     * Check email
     *
     * @param vo       CheckUserVO
     * @return call object
     */
    @POST(APIAddress.USER_VERIFICATION_EMAIL)
    Call<Object> requestCheckEmail(@Body CheckUserVO vo);

    /**
     * Check nickname
     *
     * @param vo       CheckUserVO
     * @return call object
     */
    @POST(APIAddress.USER_VERIFICATION_NICKNAME)
    Call<Object> requestCheckNickName(@Body CheckUserVO vo);

    /**
     * Check url
     *
     * @param vo       CheckUserVO
     * @return call object
     */
    @POST(APIAddress.USER_VERIFICATION_URL)
    Call<Object> requestCheckUrl(@Body CheckUserVO vo);

    /**
     * Refresh 토큰 요청
     *
     * @param vo VerificationVO
     * @return call object
     */
    @POST(APIAddress.AUTH + APIAddress.REFRESH)
    Call<AuthVO> requestAuthRefresh(@Body VerificationVO vo);

}
