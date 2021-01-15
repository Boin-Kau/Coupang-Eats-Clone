package com.example.squardcoupangeats.src.main.cart.service

import com.example.squardcoupangeats.src.main.cart.models.CartResponse
import com.example.squardcoupangeats.src.main.cart.models.PostOrderRequest
import com.example.squardcoupangeats.src.main.cart.models.PostOrderResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CartRetrofitInterface {

    @GET("/carts")
    fun getCart() : Call<CartResponse>

    @POST("/order")
    fun postOrder(@Body params : PostOrderRequest) : Call<PostOrderResponse>
}