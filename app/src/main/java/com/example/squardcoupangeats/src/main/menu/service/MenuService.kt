package com.example.squardcoupangeats.src.main.menu.service

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.src.main.menu.models.SpecificMenuResponse
import com.example.squardcoupangeats.src.main.store.models.SpecificStoreResponse
import com.example.squardcoupangeats.src.main.store.service.StoreRetrofitInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuService(val view: MenuActivityView) {

    fun tryGetSpecificMenus(index : Int) {
        val menuRetrofitInterface = ApplicationClass.sRetrofit.create(MenuRetrofitInterface::class.java)
        menuRetrofitInterface.getSpecificMenu(index).enqueue(object : Callback<SpecificMenuResponse> {
            override fun onResponse(call: Call<SpecificMenuResponse>, response: Response<SpecificMenuResponse>) {
                view.onGetSpecificMenuSuccess(response.body() as SpecificMenuResponse)
            }

            override fun onFailure(call: Call<SpecificMenuResponse>, t: Throwable) {
                view.onGetSpecificMenuFailure(t.message ?: "통신 오류")
            }
        })
    }
}