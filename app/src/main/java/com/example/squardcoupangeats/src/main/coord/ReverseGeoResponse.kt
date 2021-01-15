package com.example.squardcoupangeats.src.main.coord

import com.google.gson.annotations.SerializedName

data class ReverseGeoResponse(
    @SerializedName("status") val status : ResultStatus,
    @SerializedName("results") val results : ArrayList<ResultAddress>
)

data class ResultStatus(
    @SerializedName("code") val code : Int,
    @SerializedName("name") val name : String,
    @SerializedName("message") val message : String
)

data class ResultAddress(
    @SerializedName("name") val name : String,
    @SerializedName("code") val code : ResultCode,
    @SerializedName("region") val region : ResultRegion

)

data class ResultCode(
    @SerializedName("id") val id : String,
    @SerializedName("type") val type : String,
    @SerializedName("mappingId") val mappingId : String
)

data class ResultRegion(
    @SerializedName("area0") val area0 : ResultArea0,
    @SerializedName("area1") val area1 : ResultArea1,
    @SerializedName("area2") val area2 : ResultArea0,
    @SerializedName("area3") val area3 : ResultArea0,
    @SerializedName("area4") val area4 : ResultArea0
)

data class ResultArea0(
    @SerializedName("name") val name : String,
    @SerializedName("coords") val coords : CoordData
)

data class ResultArea1(
    @SerializedName("name") val name : String,
    @SerializedName("coords") val coords : CoordData,
    @SerializedName("alias") val alias : String
)


data class CoordData(
    @SerializedName("center") val center : ResultCenter
)

data class ResultCenter(
    @SerializedName("crs") val crs : String,
    @SerializedName("x") val x : Double,
    @SerializedName("y") val y : Double
)