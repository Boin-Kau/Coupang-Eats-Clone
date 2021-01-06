package com.example.squardcoupangeats.src.main.address

import android.os.Bundle
import android.view.View
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragmentAddressListBinding

class AddressListFragment : BaseFragment<FragmentAddressListBinding>(FragmentAddressListBinding::bind, R.layout.fragment_address_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addressSearchUsingGpsBtn.setOnClickListener {

        }
    }
}