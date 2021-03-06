package com.example.squardcoupangeats.src.main.address.service

import com.example.squardcoupangeats.src.main.address.models.AddressSearchResponse
import com.example.squardcoupangeats.src.main.address.models.PatchAddressRequest
import com.example.squardcoupangeats.src.main.address.models.PatchAddressResponse
import com.example.squardcoupangeats.src.main.home.models.StoreResponse
import retrofit2.Call
import retrofit2.http.*

interface AddressRetrofitInterface {

    @GET("v2/local/search/keyword.json")
    fun getAddress(@Header("Authorization") apiKey: String,
                   @Query("query") query : String,
                   @Query("page") page : Int,
                   @Query("size") size : Int
    ) : Call<AddressSearchResponse>

}