package com.example.squardcoupangeats.src.main.home

import com.example.squardcoupangeats.src.main.home.models.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeRetrofitInterface {

    @GET("/home")
    fun getStores(@Query("sort") sort : Int,
                  @Query("latitude") latitude : Double,
                  @Query("longitude") longitude : Double,
                  @Query("address") address : String
    ) : Call<StoreResponse>
}