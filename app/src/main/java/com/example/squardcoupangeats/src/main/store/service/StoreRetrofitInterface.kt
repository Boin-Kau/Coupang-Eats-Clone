package com.example.squardcoupangeats.src.main.store.service

import com.example.squardcoupangeats.src.main.login.models.PostLoginRequest
import com.example.squardcoupangeats.src.main.store.models.FavoriteStoreResponse
import com.example.squardcoupangeats.src.main.store.models.PostFavoriteStoreRequest
import com.example.squardcoupangeats.src.main.store.models.SpecificStoreResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StoreRetrofitInterface {

    @GET("/stores/{storeIdx}")
    fun getSpecificStore(@Path("storeIdx") storeIdx : Int) : Call<SpecificStoreResponse>

    @POST("/stores/heart")
    fun postFavoriteStore(@Body params: PostFavoriteStoreRequest) : Call<FavoriteStoreResponse>
}