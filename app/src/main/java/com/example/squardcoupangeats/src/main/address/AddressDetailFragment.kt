package com.example.squardcoupangeats.src.main.address

import android.os.Bundle
import android.view.View
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragAddressDetailBinding

class AddressDetailFragment(private val placeName : String, private val placeType : String, private val placeAddress : String) : BaseFragment<FragAddressDetailBinding>(FragAddressDetailBinding::bind, R.layout.frag_address_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragAddressDetailPlaceName.text = placeName
        binding.fragAddressDetailPlaceAddress.text = placeAddress

    }
}