package com.example.squardcoupangeats.src.main.cart.service

import com.example.squardcoupangeats.src.main.cart.models.CartResponse


interface CartActivityView {
    fun onGetCartSuccess(response: CartResponse)

    fun onGetCartFailure(message: String)
}