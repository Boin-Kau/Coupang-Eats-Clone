package com.example.squardcoupangeats.src.main.address

import com.example.squardcoupangeats.src.main.address.models.AddressSearchResponse
import com.example.squardcoupangeats.src.main.home.models.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AddressRetrofitInterface {

    @GET("v2/geocode")
    fun getAddress(@Header("X-NCP-APIGW-API-KEY-ID") clientId: String,
                   @Header("X-NCP-APIGW-API-KEY") clientSecret: String,
                   @Query("query") query : String
    ) : Call<AddressSearchResponse>
}