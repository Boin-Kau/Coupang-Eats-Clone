package com.example.squardcoupangeats.src.main.home

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.src.main.home.models.StoreResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val view: HomeFragmentView) {

    fun tryGetStores() {
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getStores(2, 37.614578, 126.835075, "KR리치빌1차아파트").enqueue(object : Callback<StoreResponse> {
            override fun onResponse(call: Call<StoreResponse>, response: Response<StoreResponse>) {
                view.onGetStoreSuccess(response.body() as StoreResponse)
            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                view.onGetStoreFailure(t.message ?: "통신 오류")
            }

        })
    }
}