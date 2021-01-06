package com.example.squardcoupangeats.src.main.address

import android.os.Bundle
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityAddressBinding
import kotlinx.android.synthetic.main.activity_address.view.*

class AddressActivity : BaseActivity<ActivityAddressBinding>(ActivityAddressBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressListFragment()).commitAllowingStateLoss()

        binding.addressEtSearchAddress.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressSearchResultFragment()).commitAllowingStateLoss()
        }

        binding.addressActivityCloseBtn.setOnClickListener {
            finish()
        }
    }
}