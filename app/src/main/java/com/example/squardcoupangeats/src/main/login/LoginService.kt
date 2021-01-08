package com.example.squardcoupangeats.src.main.login

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.src.main.login.models.LoginResponse
import com.example.squardcoupangeats.src.main.login.models.PostLoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class LoginService(val view: LoginRequestDialogView) {

    fun tryPostKakaoLogin(postLoginRequest: PostLoginRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postKakaoAccessToken(postLoginRequest).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                view.onPostLoginSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                view.onPostLoginFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostNaverLogin(postLoginRequest: PostLoginRequest) {
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postNaverAccessToken("Bearer", postLoginRequest).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                view.onPostLoginSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                view.onPostLoginFailure(t.message ?: "통신 오류")
            }

        })
    }
}