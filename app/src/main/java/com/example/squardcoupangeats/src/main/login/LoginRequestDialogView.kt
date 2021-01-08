package com.example.squardcoupangeats.src.main.login

import com.example.squardcoupangeats.src.main.login.models.LoginResponse

interface LoginRequestDialogView {

    fun onPostLoginSuccess(response: LoginResponse)

    fun onPostLoginFailure(message: String)
}