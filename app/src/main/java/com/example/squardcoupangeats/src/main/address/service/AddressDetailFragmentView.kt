package com.example.squardcoupangeats.src.main.address.service

import com.example.squardcoupangeats.src.main.address.models.PatchAddressResponse

interface AddressDetailFragmentView {

    fun onPatchAddressSuccess(response: PatchAddressResponse)

    fun onPatchAddressFailure(message : String)
}