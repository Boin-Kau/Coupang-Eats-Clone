package com.example.squardcoupangeats.src.main.favorite.service

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.src.main.favorite.models.GetFavoriteStoreResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteService(val view : FavoriteActivityView) {

    fun tryGetFavoriteStores() {
        val favoriteRetrofitInterface = ApplicationClass.sRetrofit.create(FavoriteRetrofitInterface::class.java)
        favoriteRetrofitInterface.getFavoriteStores().enqueue(object : Callback<GetFavoriteStoreResponse> {
            override fun onResponse(call: Call<GetFavoriteStoreResponse>, response: Response<GetFavoriteStoreResponse>) {
                view.onGetFavoriteStoreSuccess(response.body() as GetFavoriteStoreResponse)
            }

            override fun onFailure(call: Call<GetFavoriteStoreResponse>, t: Throwable) {
                view.onGetFavoriteStoreFailure(t.message ?: "통신 오류")
            }

        })
    }
}
