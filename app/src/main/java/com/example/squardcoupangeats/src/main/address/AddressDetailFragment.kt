package com.example.squardcoupangeats.src.main.address

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragAddressDetailBinding
import com.example.squardcoupangeats.src.main.address.models.PatchAddressRequest
import com.example.squardcoupangeats.src.main.address.models.PatchAddressResponse
import com.example.squardcoupangeats.src.main.address.service.AddressDetailFragmentView
import com.example.squardcoupangeats.src.main.address.service.AddressDetailService
import kotlin.math.roundToLong

class AddressDetailFragment(private val placeName: String, private val placeAddress: String, private val lat: String, private val lon: String)
    : BaseFragment<FragAddressDetailBinding>(FragAddressDetailBinding::bind, R.layout.frag_address_detail), AddressDetailFragmentView{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragAddressDetailPlaceName.text = placeName
        binding.fragAddressDetailPlaceAddress.text = placeAddress

        val latitude = (lat.toDouble() * 1000000).roundToLong() /1000000.0
        val longitude = (lon.toDouble() * 1000000).roundToLong() /1000000.0

        binding.fragAddressDetailPatchAddress.setOnClickListener {
            val addressDetail = binding.fragAddressDetailEtForDetail.text.toString()
            val patchAddressRequest = PatchAddressRequest(latitude, longitude, placeAddress, placeName, addressDetail)
            AddressDetailService(this).tryPatchAddress(patchAddressRequest)
        }

    }

    override fun onPatchAddressSuccess(response: PatchAddressResponse) {
        Log.d("tag", "성공 : ${response.isSuccess}")
    }

    override fun onPatchAddressFailure(message: String) {
        Log.d("오류", message)
    }
}