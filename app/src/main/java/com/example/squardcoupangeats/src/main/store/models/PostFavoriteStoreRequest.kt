package com.example.squardcoupangeats.src.main.store.models

import com.google.gson.annotations.SerializedName

data class PostFavoriteStoreRequest(
        @SerializedName("storeIdx") val storeIdx : Int
)
