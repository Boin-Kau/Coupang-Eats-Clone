package com.example.squardcoupangeats.src.main.menu.models

import com.google.gson.annotations.SerializedName

data class AddingCartResponse(
        @SerializedName("result") val result : ResultAddingCart,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)

data class ResultAddingCart(
        @SerializedName("userIdx") val userIdx : Int,
        @SerializedName("storeIdx") val storeIdx : Int,
        @SerializedName("menuIdx") val menuIdx : Int,
        @SerializedName("quantity") val quantity : Int,
        @SerializedName("option") val option : ArrayList<Int>
)