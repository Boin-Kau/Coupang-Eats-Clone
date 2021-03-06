package com.example.squardcoupangeats.src.main.store.service

import com.example.squardcoupangeats.src.main.store.models.FavoriteStoreResponse
import com.example.squardcoupangeats.src.main.store.models.SpecificStoreResponse

interface StoreActivityView {

    fun onGetSpecificStoreSuccess(response: SpecificStoreResponse)

    fun onGetSpecificStoreFailure(message: String)

    fun onPostFavoriteStoreSuccess(response: FavoriteStoreResponse)

    fun onPostFavoriteStoreFailure(message: String)
}