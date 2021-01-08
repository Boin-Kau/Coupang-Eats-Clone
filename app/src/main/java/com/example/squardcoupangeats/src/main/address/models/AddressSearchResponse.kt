package com.example.squardcoupangeats.src.main.address.models

import com.google.gson.annotations.SerializedName

data class AddressSearchResponse(
        @SerializedName("status") val status : String,
        @SerializedName("meta") val meta : ResultCount,
        @SerializedName("addresses") val address : ArrayList<ResultAddress>,
        @SerializedName("errorMessage") val errorMessage : String
)

data class ResultCount(
        @SerializedName("totalCount") val totalCount : Int,
        @SerializedName("page") val page : Int,
        @SerializedName("count") val count : Int
)

data class ResultAddress(
        @SerializedName("roadAddress") val roadAddress : String,
        @SerializedName("jibunAddress") val jibunAddress : String,
        @SerializedName("englishAddress") val englishAddress : String,
        @SerializedName("addressElements") val addressElements : ArrayList<ResultAddressElement>,
        @SerializedName("x") val x : String,
        @SerializedName("y") val y : String,
        @SerializedName("distance") val distance : Double
)

data class ResultAddressElement(
        @SerializedName("types") val types : ArrayList<String>,
        @SerializedName("longName") val longName : String,
        @SerializedName("shortName") val shortName : String,
        @SerializedName("code") val code : String
)

