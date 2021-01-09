package com.example.squardcoupangeats.src.main.address.service

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.ApplicationClass.Companion.KAKAO_REST_API_APP_KEY
import com.example.squardcoupangeats.src.main.address.models.AddressSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressService(val view: AddressActivityView) {

    fun tryGetAddress(address : String, page : Int, size : Int) {
        val addressRetrofitInterface = ApplicationClass.naverGeoRetrofit.create(AddressRetrofitInterface::class.java)
        addressRetrofitInterface.getAddress("KakaoAK "+ KAKAO_REST_API_APP_KEY, address, page, size
        ).enqueue(object : Callback<AddressSearchResponse>{
            override fun onResponse(call: Call<AddressSearchResponse>, response: Response<AddressSearchResponse>) {
                view.onGetAddressSuccess(response.body() as AddressSearchResponse)
            }

            override fun onFailure(call: Call<AddressSearchResponse>, t: Throwable) {
                view.onGetAddressFailure(t.message ?: "통신 오류")
            }
        })
    }

}