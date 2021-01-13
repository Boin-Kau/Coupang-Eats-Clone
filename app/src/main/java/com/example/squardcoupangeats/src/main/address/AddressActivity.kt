package com.example.squardcoupangeats.src.main.address

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityAddressBinding
import com.example.squardcoupangeats.src.main.address.models.AddressSearchResponse
import com.example.squardcoupangeats.src.main.address.models.ResultAddress
import com.example.squardcoupangeats.src.main.address.service.AddressActivityView
import com.example.squardcoupangeats.src.main.address.service.AddressService

@Suppress("DEPRECATION")
class AddressActivity : BaseActivity<ActivityAddressBinding>(ActivityAddressBinding::inflate), AddressActivityView, AddressClickEventInterface {

    val TAG = "tag"
    lateinit var inputMethodManager : InputMethodManager
    lateinit var addressList : ArrayList<ResultAddress>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 상태바 색상 White로 변경 / 상태바 아이콘 어둡게 설정
        this.window?.apply {
            this.statusBarColor = Color.WHITE
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        // 입력받는 방법을 관리하는 Manager객체를  요청하여 InputMethodmanager에 반환한다.
        inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressListFragment()).commitAllowingStateLoss()

        binding.addressActivityCloseBtn.setOnClickListener {
            finish()
        }

        // Custom Toolbar를 Activity의 Appbar로 지정
        setSupportActionBar(binding.addressActivityTopToolbar)

        binding.addressEtSearchAddress.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressSearchTipFragment()).commitAllowingStateLoss()
            binding.addressActivityCloseBtn.visibility = View.INVISIBLE
            binding.addressActivityBackBtnFirst.visibility = View.VISIBLE
            binding.addressActivityBackBtnSecond.visibility = View.INVISIBLE
            inputMethodManager.showSoftInput(binding.addressEtSearchAddress, 0)
        }

        binding.addressEtSearchAddress.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchAddress()
                inputMethodManager.hideSoftInputFromWindow(binding.addressEtSearchAddress.windowToken, 0)
                binding.addressEtSearchAddress.clearFocus()
                true
            }
            false
        }

        binding.addressActivityBackBtnFirst.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressListFragment()).commitAllowingStateLoss()
            binding.addressActivityCloseBtn.visibility = View.VISIBLE
            binding.addressActivityBackBtnFirst.visibility = View.INVISIBLE
            binding.addressActivityBackBtnSecond.visibility = View.INVISIBLE
            inputMethodManager.hideSoftInputFromWindow(binding.addressEtSearchAddress.windowToken, 0)
        }

        binding.addressActivityBackBtnSecond.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressSearchResultFragment(addressList, this)).commitAllowingStateLoss()
            binding.addressActivityCloseBtn.visibility = View.INVISIBLE
            binding.addressActivityBackBtnFirst.visibility = View.VISIBLE
            binding.addressActivityBackBtnSecond.visibility = View.INVISIBLE
            binding.addressActivityTitleTv.text = "배달지 주소 설정"
            binding.addressActivitySearchBarLayout.visibility = View.VISIBLE
            binding.addressActivityDividingLine.visibility = View.VISIBLE
        }
    }

    private fun searchAddress() {
        val address = binding.addressEtSearchAddress.text.toString()
        AddressService(this).tryGetAddress(address, 1, 15)
    }

    override fun onGetAddressSuccess(response: AddressSearchResponse) {
        Log.d(TAG, "성공 : ${response.meta.is_end}")

        addressList = response.documents
        supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressSearchResultFragment(addressList, this)).commitAllowingStateLoss()

    }

    override fun onGetAddressFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("오류", message)
    }

    override fun showAddressDetailFragment(placeName : String, placeAddress : String, lat : String, lon : String) {
        supportFragmentManager.beginTransaction().replace(R.id.address_frm, AddressDetailFragment(placeName, placeAddress, lat, lon)).commitAllowingStateLoss()
        binding.addressActivitySearchBarLayout.visibility = View.GONE
        binding.addressActivityDividingLine.visibility = View.GONE
        binding.addressActivityTitleTv.text = "배달지 상세 정보"
        binding.addressActivityCloseBtn.visibility = View.INVISIBLE
        binding.addressActivityBackBtnFirst.visibility = View.INVISIBLE
        binding.addressActivityBackBtnSecond.visibility = View.VISIBLE
    }
}