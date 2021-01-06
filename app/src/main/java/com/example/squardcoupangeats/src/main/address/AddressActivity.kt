package com.example.squardcoupangeats.src.main.address

import android.os.Bundle
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityAddressBinding

class AddressActivity : BaseActivity<ActivityAddressBinding>(ActivityAddressBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.addressActivityCloseBtn.setOnClickListener {
            finish()
        }
    }
}