package com.example.squardcoupangeats.src.main.favorite.models

import com.google.gson.annotations.SerializedName

data class GetFavoriteStoreResponse(
        @SerializedName("hartCount") val hartCount : Int,
        @SerializedName("hartStore") val hartStore : ArrayList<ResultHartStore>,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)

data class ResultHartStore(
        @SerializedName("storeIdx") val storeIdx : Int,
        @SerializedName("storeName") val storeName : String,
        @SerializedName("storeStar") val storeStar : Double,
        @SerializedName("reviewCount") val reviewCount: Int,
        @SerializedName("deliveryFee") val deliveryFee: String,
        @SerializedName("deliveryTime") val deliveryTime: String,
        @SerializedName("coupon") val coupon: String,
        @SerializedName("storePhoto") val storePhoto : String,
        @SerializedName("distance") val distance: String,
        @SerializedName("isCheetah") val isCheetah : String
)


