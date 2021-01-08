package com.example.squardcoupangeats.src.main.login.models

import com.google.gson.annotations.SerializedName

data class PostLoginRequest(
        @SerializedName("accessToken") val accessToken : String
)
