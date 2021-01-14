package com.example.squardcoupangeats.src.main.cart.models

import com.google.gson.annotations.SerializedName

data class CartResponse(
        @SerializedName("deliveryaddress") val deliveryAddress : ArrayList<ResultDeliveryAddress>,
        @SerializedName("storeIdx") val storeIdx : Int,
        @SerializedName("storeName") val storeName : String,
        @SerializedName("minOrderCost") val minOrderCost : String,
        @SerializedName("cartList") val cartList : ArrayList<ResultCartList>,
        @SerializedName("coupon") val coupon : ResultCoupon,
        @SerializedName("payPrice") val payPrice : ResultPayPrice,
        @SerializedName("payment") val payment : ResultPayment,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)

data class ResultCoupon(
        @SerializedName("couponCount") val couponCount : Int,
        @SerializedName("couponPrice") val couponPrice : String
)

data class ResultDeliveryAddress(
        @SerializedName("deliveryBuildingName") val deliveryBuildingName : String,
        @SerializedName("deliveryAddress") val deliveryAddressDetail : String
)

data class ResultCartList(
        @SerializedName("menuIdx") val menuIdx : Int,
        @SerializedName("quantity") val quantity : Int,
        @SerializedName("menuName") val menuName : String,
        @SerializedName("option") val option : ArrayList<String>,
        @SerializedName("price") val price : String
)

data class ResultPayPrice(
        @SerializedName("orderPrice") val orderPrice : String,
        @SerializedName("deliveryFee") val deliveryFee : String,
        @SerializedName("couponPrice") val couponPrice : String,
        @SerializedName("TotalPrice") val totalPrice : String
)

data class ResultPayment(
        @SerializedName("paymentIdx") val paymentIdx : Int,
        @SerializedName("paymentMethod") val paymentMethod : String
)