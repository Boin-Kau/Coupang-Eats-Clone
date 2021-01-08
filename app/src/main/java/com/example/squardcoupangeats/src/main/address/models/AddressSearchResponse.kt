package com.example.squardcoupangeats.src.main.address.models

import com.google.gson.annotations.SerializedName

data class AddressSearchResponse(
        @SerializedName("documents") val documents : ArrayList<ResultAddress>,
        @SerializedName("meta") val meta : ResultMeta
)

data class ResultAddress(
        @SerializedName("address_name") val address_name : String,
        @SerializedName("place_name") val place_name : String,
        @SerializedName("road_address_name") val road_address_name : String,
        @SerializedName("x") val x : String,
        @SerializedName("y") val y : String

)

data class ResultMeta(
        @SerializedName("is_end") val is_end : Boolean,
        @SerializedName("pageable_count") val pageable_count : Int,
        @SerializedName("total_count") val total_count : Int
)

