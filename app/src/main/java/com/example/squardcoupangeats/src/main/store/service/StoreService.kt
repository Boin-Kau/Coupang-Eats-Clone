package com.example.squardcoupangeats.src.main.store.service

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.src.main.home.HomeRetrofitInterface
import com.example.squardcoupangeats.src.main.home.models.StoreResponse
import com.example.squardcoupangeats.src.main.store.models.FavoriteStoreResponse
import com.example.squardcoupangeats.src.main.store.models.PostFavoriteStoreRequest
import com.example.squardcoupangeats.src.main.store.models.SpecificStoreResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreService(val view: StoreActivityView) {

    fun tryGetSpecificStores(index : Int) {
        val storeRetrofitInterface = ApplicationClass.sRetrofit.create(StoreRetrofitInterface::class.java)
        storeRetrofitInterface.getSpecificStore(index).enqueue(object : Callback<SpecificStoreResponse> {
            override fun onResponse(call: Call<SpecificStoreResponse>, response: Response<SpecificStoreResponse>) {
                view.onGetSpecificStoreSuccess(response.body() as SpecificStoreResponse)
            }

            override fun onFailure(call: Call<SpecificStoreResponse>, t: Throwable) {
                view.onGetSpecificStoreFailure(t.message ?: "통신 오류")
            }

        })
    }

    fun tryPostFavoriteStore(postFavoriteStoreRequest: PostFavoriteStoreRequest) {
        val storeRetrofitInterface = ApplicationClass.sRetrofit.create(StoreRetrofitInterface::class.java)
        storeRetrofitInterface.postFavoriteStore(postFavoriteStoreRequest).enqueue(object : Callback<FavoriteStoreResponse> {
            override fun onResponse(call: Call<FavoriteStoreResponse>, response: Response<FavoriteStoreResponse>) {
                view.onPostFavoriteStoreSuccess(response.body() as FavoriteStoreResponse)
            }

            override fun onFailure(call: Call<FavoriteStoreResponse>, t: Throwable) {
                view.onPostFavoriteStoreFailure(t.message ?: "통신 오류")
            }

        })
    }
}