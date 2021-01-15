package com.example.squardcoupangeats.src.main.coord

import com.example.squardcoupangeats.src.main.address.models.AddressSearchResponse

interface ReverseGeoView {

    fun onGetReverseGeoSuccess(response: ReverseGeoResponse)

    fun onGetReverseGeoFailure(message: String)
}
