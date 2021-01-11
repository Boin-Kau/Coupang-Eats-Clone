package com.example.squardcoupangeats.src.main.menu.service

import com.example.squardcoupangeats.src.main.menu.models.SpecificMenuResponse
import com.example.squardcoupangeats.src.main.store.models.SpecificStoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuRetrofitInterface {

    @GET("/menus/{menuIdx}")
    fun getSpecificMenu(@Path("menuIdx") menuIdx : Int) : Call<SpecificMenuResponse>
}