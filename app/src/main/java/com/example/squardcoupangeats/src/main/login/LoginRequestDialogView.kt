package com.example.squardcoupangeats.src.main.login

import com.example.squardcoupangeats.src.main.login.models.KakaoLoginResponse

interface LoginRequestDialogView {

    fun onPostLoginSuccess(response: KakaoLoginResponse)

    fun onPostLoginFailure(message: String)
}