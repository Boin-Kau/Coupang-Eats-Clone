package com.example.squardcoupangeats.src.main.address.service

import com.example.squardcoupangeats.src.main.address.models.PatchAddressRequest
import com.example.squardcoupangeats.src.main.address.models.PatchAddressResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PATCH

interface AddressDetailRetrofitInterface {

    @PATCH("/address")
    fun patchAddress(@Body params : PatchAddressRequest) : Call<PatchAddressResponse>
}