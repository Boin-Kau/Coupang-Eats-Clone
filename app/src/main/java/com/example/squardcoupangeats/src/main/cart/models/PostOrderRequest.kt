package com.example.squardcoupangeats.src.main.cart.models

import com.google.gson.annotations.SerializedName

data class PostOrderRequest(
    @SerializedName("toStore") val toStore : String,
    @SerializedName("noPlastic") val noPlastic : String,
    @SerializedName("deliveryReqIdx") val deliveryReqIdx : Int,
    @SerializedName("receiptId") val receiptId : String
)
