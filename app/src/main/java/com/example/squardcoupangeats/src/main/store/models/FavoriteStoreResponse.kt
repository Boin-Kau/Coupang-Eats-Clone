package com.example.squardcoupangeats.src.main.store.models

import com.google.gson.annotations.SerializedName

data class FavoriteStoreResponse(
        @SerializedName("result") val result : ResultFavoriteStore,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)

data class ResultFavoriteStore(
        @SerializedName("storeIdx") val storeIdx : Int,
        @SerializedName("userIdx") val userIdx : Int
)
