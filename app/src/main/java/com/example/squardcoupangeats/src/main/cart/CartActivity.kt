package com.example.squardcoupangeats.src.main.cart

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.ApplicationClass.Companion.BOOT_PAY_APPLICATION_ID
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityCartBinding
import com.example.squardcoupangeats.src.main.cart.adapter.CartListAdapter
import com.example.squardcoupangeats.src.main.cart.models.*
import com.example.squardcoupangeats.src.main.cart.service.CartActivityView
import com.example.squardcoupangeats.src.main.cart.service.CartService
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.kakao.sdk.common.util.Utility.getJson
import kr.co.bootpay.Bootpay
import kr.co.bootpay.BootpayAnalytics
import kr.co.bootpay.enums.Method
import kr.co.bootpay.enums.PG
import kr.co.bootpay.enums.UX
import kr.co.bootpay.model.BootExtra
import kr.co.bootpay.model.BootUser
import org.json.JSONObject

class CartActivity : BaseActivity<ActivityCartBinding>(ActivityCartBinding::inflate), CartActivityView {

    val TAG = "tag"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val params = window!!.attributes
        params.windowAnimations = R.style.AnimationPopupStyle
        window!!.attributes = params

        CartService(this).tryGetCart()

        BootpayAnalytics.init(this, BOOT_PAY_APPLICATION_ID)
        binding.cartActivityPayBtn.setOnClickListener {
            goBootpayRequest()
        }
    }

    private fun goBootpayRequest() {
        val bootUser = BootUser().setPhone("010-2934-1246")
        val bootExtra = BootExtra().setQuotas(intArrayOf(0, 2, 3))

        val stuck = 1 //재고 있음

        Bootpay.init(this)
                .setApplicationId(BOOT_PAY_APPLICATION_ID) // 해당 프로젝트(안드로이드)의 application id 값
                .setContext(this)
                .setBootUser(bootUser)
                .setBootExtra(bootExtra)
                .setUX(UX.PG_DIALOG)
                .setPG(PG.DANAL)
                .setMethod(Method.CARD)
                .setName("(셋트)선택2마리") // 결제할 상품명
                .setOrderId("1234") // 결제 고유번호expire_month
                .setPrice(21500) // 결제할 금액
//                .addItem("마우'", 1, "ITEM_CODE_MOUSE", 100) // 주문정보에 담길 상품정보, 통계를 위해 사용
//                .addItem("키보드", 1, "ITEM_CODE_KEYBOARD", 200, "패션", "여성상의", "블라우스") // 주문정보에 담길 상품정보, 통계를 위해 사용
                .onConfirm { message ->
                    if (0 < stuck) Bootpay.confirm(message); // 재고가 있을 경우.
                    else Bootpay.removePaymentWindow() // 재고가 없어 중간에 결제창을 닫고 싶을 경우
                    Log.d("confirm", message)

                    val jObject = JSONObject(message)
                    val id = jObject.getString("receipt_id")
                    Log.d(TAG, "파싱 성공 $id")

                    CartService(this).tryPostOrder(PostOrderRequest("", "Y", 1, id))

                }
                .onDone() { message ->
                    Log.d("done", message)
                }
                .onReady { message ->
                    Log.d("ready", message)
                }
                .onCancel { message ->
                    Log.d("cancel", message)
                }
                .onError{ message ->
                    Log.d("error", message)
                }
                .onClose { message ->
                    Log.d("close", "close")
                }
                .request()





    }

    override fun onGetCartSuccess(response: CartResponse) {
        Log.d(TAG, "Get Cart 성공 : ${response.message}")

        val addressData = response.deliveryAddress[0]
        val cartList = response.cartList
        val couponData = response.coupon
        val payPrice = response.payPrice
        binding.cartActivityStoreName.text = response.storeName
        binding.cartActivityAmountOfCoupon.text = couponData.couponCount.toString()
        binding.cartActivityCardNum.text = response.payment.paymentMethod
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
        binding.cartActivityTotalPrice.text = payPrice.totalPrice
        binding.cartActivityPayBtnTotalPrice.text = payPrice.totalPrice
    }

    private fun setCartList(cartList: ArrayList<ResultCartList>) {
        val cartListAdapter = CartListAdapter(cartList)
        binding.cartActivityCartListRecyclerview.adapter = cartListAdapter
    }

    private fun setAddress(addressData: ResultDeliveryAddress) {
        binding.cartActivityDeliveryBuildingName.text = addressData.deliveryBuildingName
        binding.cartActivityDeliveryAddress.text = addressData.deliveryAddressDetail

    }

    override fun onGetCartFailure(message: String) {
        Log.d("오류", message)
    }

    override fun onPostOrderSuccess(response: PostOrderResponse) {
        Log.d(TAG, "Post Order 성공 : ${response.isSuccess}")
        finish()

    }

    override fun onPostOrderFailure(message: String) {
        Log.d("오류", message)
    }
}