package com.example.squardcoupangeats.src.main.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {

    @POST("/kakao-login")
    fun postKakaoAccessToke(@Body params: PostKakaoLoginRequest) : Call<ResultKakaoLogin>
}