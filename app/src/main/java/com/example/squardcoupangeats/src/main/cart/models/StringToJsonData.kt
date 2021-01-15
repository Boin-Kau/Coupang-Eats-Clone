package com.example.squardcoupangeats.src.main.cart.models

import com.google.gson.annotations.SerializedName

data class StringToJsonData(
    @SerializedName("receipt_id") val receipt_id : String,
    @SerializedName("params") val params : String,
    @SerializedName("action") val action : String
)

