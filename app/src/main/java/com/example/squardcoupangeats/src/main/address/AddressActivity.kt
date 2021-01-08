package com.example.squardcoupangeats.src.main.address

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityAddressBinding
import kotlinx.android.synthetic.main.activity_address.view.*

class AddressActivity : BaseActivity<ActivityAddressBinding>(ActivityAddressBinding::inflate) {

    lateinit var inputMethodManager : InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 입력받는 방법을 관리하는 Manager객체를  요청하여 InputMethodmanager에 반환한다.
        inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager


        supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressListFragment()).commitAllowingStateLoss()

        binding.addressActivityCloseBtn.setOnClickListener {
            finish()
        }

        // Custom Toolbar를 Activity의 Appbar로 지정
        setSupportActionBar(binding.addressActivityTopToolbar)

        binding.addressEtSearchAddress.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressSearchResultFragment()).commitAllowingStateLoss()
            binding.addressActivityCloseBtn.visibility = View.INVISIBLE
            binding.addressActivityBackBtn.visibility = View.VISIBLE
            inputMethodManager.showSoftInput(binding.addressEtSearchAddress, 0)
        }

        binding.addressActivityBackBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressListFragment()).commitAllowingStateLoss()
            binding.addressActivityCloseBtn.visibility = View.VISIBLE
            binding.addressActivityBackBtn.visibility = View.INVISIBLE
            inputMethodManager.hideSoftInputFromWindow(binding.addressEtSearchAddress.windowToken, 0)
        }


    }
}