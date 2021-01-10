package com.example.squardcoupangeats.src.main.address

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.ApplicationClass.Companion.searchedAddressList
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragmentAddressListBinding
import com.example.squardcoupangeats.src.main.MainActivity
import com.example.squardcoupangeats.src.main.address.adapter.AddressListAdapter

class AddressListFragment : BaseFragment<FragmentAddressListBinding>(FragmentAddressListBinding::bind, R.layout.fragment_address_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addressSearchUsingGpsBtn.setOnClickListener {
            activity!!.startActivity(Intent(activity, NaverMapActivity::class.java))
        }

        val listAdapter = AddressListAdapter(searchedAddressList)
        binding.addressListFragRecyclerview.adapter = listAdapter
        listAdapter.searchedAddressListItemClick = object : AddressListAdapter.SearchedAddressListItemClick {
            override fun onClick(view: View, position: Int) {
                val placeName = searchedAddressList[position].placeName
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("placeName", placeName)
                startActivity(intent)
                activity!!.finish()
            }
        }
    }
}