package com.example.squardcoupangeats.src.main.cart.models


import com.google.gson.annotations.SerializedName

data class PostOrderResponse(
    @SerializedName("result") val result: ResultOrder,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class ResultOrder(
    @SerializedName("orderIdx") val orderIdx : Int
)