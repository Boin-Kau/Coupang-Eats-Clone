package com.example.squardcoupangeats.src.main.menu.models

import com.google.gson.annotations.SerializedName

data class PostAddingCartRequest(
        @SerializedName("storeIdx") val storeIdx: Int,
        @SerializedName("menuIdx") val menuIdx: Int,
        @SerializedName("quantity") val quantity: Int,
        @SerializedName("optionList") val optionList: ArrayList<Int>
)
