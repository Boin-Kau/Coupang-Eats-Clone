package com.example.squardcoupangeats.src.main.cart.service

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.src.main.cart.models.CartResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartService(val view : CartActivityView) {

    fun tryGetCart() {
        val cartRetrofitInterface = ApplicationClass.sRetrofit.create(CartRetrofitInterface::class.java)
        cartRetrofitInterface.getCart().enqueue(object : Callback<CartResponse>{
            override fun onResponse(call: Call<CartResponse>, response: Response<CartResponse>) {
                view.onGetCartSuccess(response.body() as CartResponse)
            }

            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                view.onGetCartFailure(t.message ?: "통신 오류")
            }

        })
    }
}