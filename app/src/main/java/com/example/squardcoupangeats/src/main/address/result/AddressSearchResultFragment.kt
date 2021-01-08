package com.example.squardcoupangeats.src.main.address.result

import android.os.Bundle
import android.view.View
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragmentAddressSearchResultBinding
import com.example.squardcoupangeats.src.main.address.models.ResultAddress

class AddressSearchResultFragment(private val addressList : ArrayList<ResultAddress>) : BaseFragment<FragmentAddressSearchResultBinding>(FragmentAddressSearchResultBinding::bind, R.layout.fragment_address_search_result) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchResultAdapter = AddressSearchResultAdapter(addressList)
        binding.fragAddressSearchResultRecyclerview.adapter = searchResultAdapter
    }



}