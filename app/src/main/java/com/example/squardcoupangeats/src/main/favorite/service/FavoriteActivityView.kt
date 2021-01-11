package com.example.squardcoupangeats.src.main.favorite.service

import com.example.squardcoupangeats.src.main.favorite.models.GetFavoriteStoreResponse
import com.example.squardcoupangeats.src.main.home.models.StoreResponse

interface FavoriteActivityView {

    fun onGetFavoriteStoreSuccess(response: GetFavoriteStoreResponse)

    fun onGetFavoriteStoreFailure(message: String)
}