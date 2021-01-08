package com.example.squardcoupangeats.src.main.address.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragmentAddressListBinding
import com.example.squardcoupangeats.src.main.address.NaverMapActivity

class AddressListFragment : BaseFragment<FragmentAddressListBinding>(FragmentAddressListBinding::bind, R.layout.fragment_address_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.addressSearchUsingGpsBtn.setOnClickListener {
            activity!!.startActivity(Intent(activity, NaverMapActivity::class.java))
        }
    }
}