package com.example.squardcoupangeats.src.main.cart.service

import com.example.squardcoupangeats.src.main.cart.models.CartResponse
import com.example.squardcoupangeats.src.main.cart.models.PostOrderResponse


interface CartActivityView {
    fun onGetCartSuccess(response: CartResponse)

    fun onGetCartFailure(message: String)

    fun onPostOrderSuccess(response: PostOrderResponse)

    fun onPostOrderFailure(message: String)
}