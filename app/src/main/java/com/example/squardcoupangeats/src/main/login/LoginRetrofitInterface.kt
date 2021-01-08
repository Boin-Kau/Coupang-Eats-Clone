package com.example.squardcoupangeats.src.main.login

import com.example.squardcoupangeats.src.main.login.models.LoginResponse
import com.example.squardcoupangeats.src.main.login.models.PostLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginRetrofitInterface {

    @POST("/kakao-login")
    fun postKakaoAccessToken(@Body params: PostLoginRequest) : Call<LoginResponse>

    @POST("/naver-login")
    fun postNaverAccessToken(@Header("Authorization") auth : String,
                             @Body params: PostLoginRequest) : Call<LoginResponse>
}