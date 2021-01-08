package com.example.squardcoupangeats.src.main.address

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.ApplicationClass.Companion.NAVER_API_CLIENT_ID
import com.example.squardcoupangeats.config.ApplicationClass.Companion.NAVER_API_CLIENT_SECRET
import com.example.squardcoupangeats.src.main.address.models.AddressSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressService(val view: AddressActivityView, private val searchQuery: String) {

    fun tryGetAddress() {
        val addressRetrofitInterface = ApplicationClass.naverGeoRetrofit.create(AddressRetrofitInterface::class.java)
        addressRetrofitInterface.getAddress(NAVER_API_CLIENT_ID, NAVER_API_CLIENT_SECRET, searchQuery).enqueue(object : Callback<AddressSearchResponse>{
            override fun onResponse(call: Call<AddressSearchResponse>, response: Response<AddressSearchResponse>) {
                view.onGetAddressSuccess(response.body() as AddressSearchResponse)
            }

            override fun onFailure(call: Call<AddressSearchResponse>, t: Throwable) {
                view.onGetAddressFailure(t.message ?: "통신 오류")
            }
        })
    }

}