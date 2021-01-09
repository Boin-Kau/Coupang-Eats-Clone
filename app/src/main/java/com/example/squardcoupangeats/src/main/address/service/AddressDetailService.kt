package com.example.squardcoupangeats.src.main.address.service

import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.src.main.address.models.PatchAddressRequest
import com.example.squardcoupangeats.src.main.address.models.PatchAddressResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressDetailService(val view: AddressDetailFragmentView) {

    fun tryPatchAddress(patchAddressRequest: PatchAddressRequest) {
        val addressDetailRetrofitInterface = ApplicationClass.sRetrofit.create(AddressDetailRetrofitInterface::class.java)
        addressDetailRetrofitInterface.patchAddress(patchAddressRequest).enqueue(object : Callback<PatchAddressResponse>{
            override fun onResponse(call: Call<PatchAddressResponse>, response: Response<PatchAddressResponse>) {
                view.onPatchAddressSuccess(response.body() as PatchAddressResponse)
            }

            override fun onFailure(call: Call<PatchAddressResponse>, t: Throwable) {
                view.onPatchAddressFailure(t.message ?: "통신 오류")
            }
        })

    }
}