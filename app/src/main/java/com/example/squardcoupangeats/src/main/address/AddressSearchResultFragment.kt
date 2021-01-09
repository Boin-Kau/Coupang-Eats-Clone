package com.example.squardcoupangeats.src.main.address

import android.os.Bundle
import android.view.View
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragmentAddressSearchResultBinding
import com.example.squardcoupangeats.src.main.address.adapter.AddressSearchResultAdapter
import com.example.squardcoupangeats.src.main.address.adapter.SearchedAddressData
import com.example.squardcoupangeats.src.main.address.models.ResultAddress
import kotlinx.android.synthetic.main.list_address_search_result_frag_recyclerview.*

class AddressSearchResultFragment(private val addressList : ArrayList<ResultAddress>, val clickEventInterface: AddressClickEventInterface) : BaseFragment<FragmentAddressSearchResultBinding>(FragmentAddressSearchResultBinding::bind, R.layout.fragment_address_search_result) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchResultAdapter = AddressSearchResultAdapter(addressList)
        binding.fragAddressSearchResultRecyclerview.adapter = searchResultAdapter
        searchResultAdapter.addressSearchResultItemClick = object : AddressSearchResultAdapter.AddressSearchResultItemClick {
            override fun onClick(view: View, position: Int) {
                val placeName = addressList[position].place_name
                val placeType : String
                val placeAddress : String
                if(addressList[position].road_address_name == "") {
                    placeType = "지번"
                    placeAddress = addressList[position].address_name
                } else {
                    placeType = "도로명"
                    placeAddress = addressList[position].road_address_name
                }
                val lat = addressList[position].y
                val lon = addressList[position].x
                clickEventInterface.showAddressDetailFragment(placeName, placeAddress, lat, lon)

                ApplicationClass.searchedAddressList.add(SearchedAddressData(placeName, placeAddress))
            }

        }
    }



}