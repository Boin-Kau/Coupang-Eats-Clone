package com.example.squardcoupangeats.src.main.favorite.service

import com.example.squardcoupangeats.src.main.favorite.models.GetFavoriteStoreResponse
import com.example.squardcoupangeats.src.main.home.models.StoreResponse
import retrofit2.Call
import retrofit2.http.GET

interface FavoriteRetrofitInterface {

    @GET("/harts")
    fun getFavoriteStores() : Call<GetFavoriteStoreResponse>
}