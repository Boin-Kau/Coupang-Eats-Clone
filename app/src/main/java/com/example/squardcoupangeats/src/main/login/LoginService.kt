package com.example.squardcoupangeats.src.main.login

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.src.main.login.models.KakaoLoginResponse
import com.example.squardcoupangeats.src.main.login.models.PostKakaoLoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view: LoginRequestDialogView) {

    fun tryPostKakaoLogin(postKakaoLoginRequest: PostKakaoLoginRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postKakaoAccessToken(postKakaoLoginRequest).enqueue(object : Callback<KakaoLoginResponse>{
            override fun onResponse(call: Call<KakaoLoginResponse>, response: Response<KakaoLoginResponse>) {
                view.onPostLoginSuccess(response.body() as KakaoLoginResponse)
            }

            override fun onFailure(call: Call<KakaoLoginResponse>, t: Throwable) {
                view.onPostLoginFailure(t.message ?: "통신 오류")
            }

        })
    }
}