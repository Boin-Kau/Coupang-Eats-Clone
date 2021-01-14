package com.example.squardcoupangeats.src.main.cart

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityCartBinding
import com.example.squardcoupangeats.src.main.cart.models.*
import com.example.squardcoupangeats.src.main.cart.service.CartActivityView
import com.example.squardcoupangeats.src.main.cart.service.CartService

class CartActivity : BaseActivity<ActivityCartBinding>(ActivityCartBinding::inflate), CartActivityView {

    val TAG = "tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = window!!.attributes
        params.windowAnimations = R.style.AnimationPopupStyle
        window!!.attributes = params

        CartService(this).tryGetCart()
    }

    override fun onGetCartSuccess(response: CartResponse) {
        Log.d(TAG, "Get Cart 성공 : ${response.message}")

        val addressData = response.deliveryAddress[0]
        val cartList = response.cartList
        val couponData = response.coupon
        val payPrice = response.payPrice
        binding.cartActivityStoreName.text = response.storeName
        binding.cartActivityAmountOfCoupon.text = couponData.couponCount.toString()
        setAddress(addressData)
        setCartList(cartList)
        setPayPrice(payPrice)

    }

    private fun setPayPrice(payPrice: ResultPayPrice) {
        binding.cartActivityOrderPrice.text = payPrice.orderPrice
        binding.cartActivityDeliveryFee.text = payPrice.deliveryFee
        if(payPrice.couponPrice != null) {
            binding.cartActivityCouponPrice.text = payPrice.couponPrice
        } else {
            binding.cartActivityCouponLayout.visibility = View.GONE
        }
    }

    private fun setCartList(cartList: ArrayList<ResultCartList>) {
        // 리사이클러뷰
    }

    private fun setAddress(addressData: ResultDeliveryAddress) {
        binding.cartActivityDeliveryBuildingName.text = addressData.deliveryBuildingName
        binding.cartActivityDeliveryAddress.text = addressData.deliveryAddressDetail

    }

    override fun onGetCartFailure(message: String) {
        Log.d("오류", message)
    }
}