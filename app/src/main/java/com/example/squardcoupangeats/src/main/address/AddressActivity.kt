package com.example.squardcoupangeats.src.main.address

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityAddressBinding
import com.example.squardcoupangeats.src.main.address.list.AddressListFragment
import com.example.squardcoupangeats.src.main.address.models.AddressSearchResponse
import com.example.squardcoupangeats.src.main.address.result.AddressSearchResultFragment

class AddressActivity : BaseActivity<ActivityAddressBinding>(ActivityAddressBinding::inflate), AddressActivityView {

    val TAG = "tag"
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

        binding.addressEtSearchAddress.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchAddress()
                true
            }
            false
        }

        binding.addressActivityBackBtn.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressListFragment()).commitAllowingStateLoss()
            binding.addressActivityCloseBtn.visibility = View.VISIBLE
            binding.addressActivityBackBtn.visibility = View.INVISIBLE
            inputMethodManager.hideSoftInputFromWindow(binding.addressEtSearchAddress.windowToken, 0)
        }


    }

    private fun searchAddress() {
        val address = binding.addressEtSearchAddress.text.toString()
        AddressService(this, address).tryGetAddress()
    }

    override fun onGetAddressSuccess(response: AddressSearchResponse) {
        Log.d(TAG, "성공 : ${response.address[0].roadAddress}")
    }

    override fun onGetAddressFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("오류", message)
    }


}