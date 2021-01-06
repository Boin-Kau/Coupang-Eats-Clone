package com.example.squardcoupangeats.src.main.login

import com.google.gson.annotations.SerializedName

data class PostKakaoLoginRequest(
        @SerializedName("accessToken") val kakaoAccessToken : String
)
