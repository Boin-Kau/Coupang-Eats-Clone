package com.example.squardcoupangeats.src.main.login

import com.example.squardcoupangeats.src.main.login.models.KakaoLoginResponse
import com.example.squardcoupangeats.src.main.login.models.PostKakaoLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {

    @POST("/kakao-login")
    fun postKakaoAccessToken(@Body params: PostKakaoLoginRequest) : Call<KakaoLoginResponse>
}