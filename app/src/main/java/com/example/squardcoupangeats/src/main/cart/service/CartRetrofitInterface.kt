package com.example.squardcoupangeats.src.main.cart.service

import com.example.squardcoupangeats.src.main.cart.models.CartResponse
import retrofit2.Call
import retrofit2.http.GET

interface CartRetrofitInterface {

    @GET("/carts")
    fun getCart() : Call<CartResponse>
}