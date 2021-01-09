package com.example.squardcoupangeats.src.main.address

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragAddressDetailBinding
import com.example.squardcoupangeats.src.main.MainActivity
import com.example.squardcoupangeats.src.main.address.models.PatchAddressRequest
import com.example.squardcoupangeats.src.main.address.models.PatchAddressResponse
import com.example.squardcoupangeats.src.main.address.service.AddressDetailFragmentView
import com.example.squardcoupangeats.src.main.address.service.AddressDetailService
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToLong

class AddressDetailFragment(private val placeName: String, private val placeAddress: String, private val lat: String, private val lon: String)
    : BaseFragment<FragAddressDetailBinding>(FragAddressDetailBinding::bind, R.layout.frag_address_detail), AddressDetailFragmentView{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragAddressDetailPlaceName.text = placeName
        binding.fragAddressDetailPlaceAddress.text = placeAddress

        val latitude = BigDecimal(lat).setScale(6, RoundingMode.HALF_EVEN)
        val longitude = BigDecimal(lon).setScale(6, RoundingMode.HALF_EVEN)
        Log.d("tag", "testLat : ${latitude.toDouble()}, testLon : ${longitude.toDouble()}")


        binding.fragAddressDetailPatchAddress.setOnClickListener {
            val addressDetail = binding.fragAddressDetailEtForDetail.text.toString()
            val patchAddressRequest = PatchAddressRequest(latitude.toDouble(), longitude.toDouble(), placeAddress, placeName, addressDetail)
            AddressDetailService(this).tryPatchAddress(patchAddressRequest)

            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("placeName", placeName)
            startActivity(intent)
            activity!!.finish()
        }

    }

    override fun onPatchAddressSuccess(response: PatchAddressResponse) {
        Log.d("tag", "Address Patch 성공 : ${response.isSuccess}")
    }

    override fun onPatchAddressFailure(message: String) {
        Log.d("오류", message)
    }
}