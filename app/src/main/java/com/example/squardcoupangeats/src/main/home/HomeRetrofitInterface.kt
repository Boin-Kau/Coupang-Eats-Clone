package com.example.squardcoupangeats.src.main.home

import com.example.squardcoupangeats.src.main.home.models.StoreResponse
import retrofit2.Call
import retrofit2.http.GET

interface HomeRetrofitInterface {

    @GET("/stores")
    fun getStores() : Call<StoreResponse>
}