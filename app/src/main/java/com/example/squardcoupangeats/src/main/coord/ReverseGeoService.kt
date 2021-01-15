package com.example.squardcoupangeats.src.main.coord

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.ApplicationClass.Companion.NAVER_API_CLIENT_ID
import com.example.squardcoupangeats.config.ApplicationClass.Companion.NAVER_API_CLIENT_SECRET
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReverseGeoService(val view : ReverseGeoView) {

    fun tryGetReverseGeo(coords : String) {
        val reverseGeoInterface = ApplicationClass.naverGeoRetrofit.create(ReverseGeoInterface::class.java)
        reverseGeoInterface.getCurrentAddress(NAVER_API_CLIENT_ID, NAVER_API_CLIENT_SECRET, coords, "json")
            .enqueue(object : Callback<ReverseGeoResponse>{
                override fun onResponse(call: Call<ReverseGeoResponse>, response: Response<ReverseGeoResponse>) {
                    view.onGetReverseGeoSuccess(response.body() as ReverseGeoResponse)
                }
                override fun onFailure(call: Call<ReverseGeoResponse>, t: Throwable) {
                    view.onGetReverseGeoFailure(t.message ?: "통신 오류")
                }

            })
    }
}