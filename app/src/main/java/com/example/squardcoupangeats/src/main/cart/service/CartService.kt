package com.example.squardcoupangeats.src.main.cart.service

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.src.main.cart.models.CartResponse
import com.example.squardcoupangeats.src.main.cart.models.PostOrderRequest
import com.example.squardcoupangeats.src.main.cart.models.PostOrderResponse
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

    fun tryPostOrder(request: PostOrderRequest) {
        val cartRetrofitInterface = ApplicationClass.sRetrofit.create(CartRetrofitInterface::class.java)
        cartRetrofitInterface.postOrder(request).enqueue(object :Callback<PostOrderResponse>{
            override fun onResponse(call: Call<PostOrderResponse>, response: Response<PostOrderResponse>) {
                view.onPostOrderSuccess(response.body() as PostOrderResponse)
            }

            override fun onFailure(call: Call<PostOrderResponse>, t: Throwable) {
                view.onPostOrderFailure(t.message ?: "통신 오류")
            }

        })
    }
}