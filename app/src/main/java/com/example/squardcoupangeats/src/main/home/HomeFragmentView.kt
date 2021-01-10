package com.example.squardcoupangeats.src.main.home

import com.example.squardcoupangeats.src.main.home.models.StoreResponse

interface HomeFragmentView {

    fun onGetStoreSuccess(response: StoreResponse)

    fun onGetStoreFailure(message: String)
}