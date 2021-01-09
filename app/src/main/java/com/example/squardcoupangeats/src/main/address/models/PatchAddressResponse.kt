package com.example.squardcoupangeats.src.main.address.models

import com.google.gson.annotations.SerializedName

data class PatchAddressResponse(
        @SerializedName("isSuccess") val isSuccess : Boolean,
        @SerializedName("code") val code : Int,
        @SerializedName("message") val message : String
)
