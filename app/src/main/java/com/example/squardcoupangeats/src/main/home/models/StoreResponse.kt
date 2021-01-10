package com.example.squardcoupangeats.src.main.home.models

import com.google.gson.annotations.SerializedName

data class StoreResponse(
        @SerializedName("result") val result: ResultStore,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)
