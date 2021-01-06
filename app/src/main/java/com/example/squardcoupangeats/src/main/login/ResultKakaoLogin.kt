package com.example.squardcoupangeats.src.main.login

import com.google.gson.annotations.SerializedName

data class ResultKakaoLogin(
        @SerializedName("result") val result : ResultJwt,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)

data class ResultJwt(
        @SerializedName("jwt") val jwt : String
)
