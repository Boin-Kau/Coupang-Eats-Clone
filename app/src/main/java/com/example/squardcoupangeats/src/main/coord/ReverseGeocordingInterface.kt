package com.example.squardcoupangeats.src.main.coord


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ReverseGeoInterface {
    @GET("gc/")
    fun getCurrentAddress(@Header("X-NCP-APIGW-API-KEY-ID") clientId : String,
                          @Header("X-NCP-APIGW-API-KEY") clientPw : String,
                          @Query("coords") coords : String,
                          @Query("output") output : String
    ) : Call<ReverseGeoResponse>
}