package com.example.squardcoupangeats.src.main.address.models

import com.google.gson.annotations.SerializedName

data class PatchAddressRequest(
        @SerializedName("latitude") val latitude : Double,
        @SerializedName("longitude") val longitude : Double,
        @SerializedName("address") val address : String,
        @SerializedName("buildingName") val buildingName : String,
        @SerializedName("addressDetail") val addressDetail : String
)
