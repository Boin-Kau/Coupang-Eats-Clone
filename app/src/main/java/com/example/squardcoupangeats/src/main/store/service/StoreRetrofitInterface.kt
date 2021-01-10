package com.example.squardcoupangeats.src.main.store.service

import com.example.squardcoupangeats.src.main.store.models.SpecificStoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreRetrofitInterface {

    @GET("/stores/{storeIdx}")
    fun getSpecificStore(@Path("storeIdx") storeIdx : Int) : Call<SpecificStoreResponse>
}