package com.example.squardcoupangeats.src.main.address.service

import com.example.squardcoupangeats.src.main.address.models.AddressSearchResponse

interface AddressActivityView {

    fun onGetAddressSuccess(response: AddressSearchResponse)

    fun onGetAddressFailure(message: String)
}