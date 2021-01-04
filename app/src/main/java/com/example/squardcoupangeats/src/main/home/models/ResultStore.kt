package com.example.squardcoupangeats.src.main.home.models

import com.google.gson.annotations.SerializedName

data class ResultStore(
        @SerializedName("promotion") val promotion: ArrayList<PromotionData>,
        @SerializedName("category") val category: ArrayList<CategoryData>,
        @SerializedName("franchise") val franchise: ArrayList<FranchiseData>,
        @SerializedName("openStore") val openStore: ArrayList<NewStoreData>,
        @SerializedName("mainStore") val sortedStore: ArrayList<SortedStoreData>
)

data class PromotionData(
        @SerializedName("promotionIdx") val promotionIndex: Int,
        @SerializedName("title") val promotionTitle: String,
        @SerializedName("promotionPhoto") val promotionUrl: String
)

data class CategoryData(
        @SerializedName("storeCatIdx") val categoryIndex: Int,
        @SerializedName("storeCatName") val categoryName: String
)

data class FranchiseData(
        @SerializedName("storeIdx") val franchiseIndex: Int,
        @SerializedName("storeName") val franchiseName: String,
        @SerializedName("storeStar") val franchiseStar: Double,
        @SerializedName("reviewCount") val franchiseReviewCnt: Int,
        @SerializedName("deliveryFee") val franchiseDeliveryFee: String,
        @SerializedName("coupon") val franchiseCouponInfo: String,
        @SerializedName("storePhoto") val franchisePhotoUrl: String,
        @SerializedName("distance") val franchiseDistance: String
)

data class NewStoreData(
        @SerializedName("storeIdx") val newStoreIndex: Int,
        @SerializedName("storeName") val newStoreName: String,
        @SerializedName("deliveryFee") val newStoreDeliveryFee: String,
        @SerializedName("coupon") val newStoreCouponInfo: String,
        @SerializedName("storePhoto") val newStorePhotoUrl: String,
        @SerializedName("distance") val newStoreDistance: String
)

data class SortedStoreData(
        @SerializedName("storeIdx") val sortedStoreIndex: Int,
        @SerializedName("storeName") val sortedStoreName: String,
        @SerializedName("storeStar") val sortedStoreStar: Double,
        @SerializedName("reviewCount") val sortedStoreReviewCnt: Int,
        @SerializedName("deliveryFee") val sortedStoreDeliveryFee: String,
        @SerializedName("deliveryTime") val sortedStoreDeliveryTime: String,
        @SerializedName("coupon") val sortedStoreCouponInfo: String,
        @SerializedName("distance") val sortedStoreDistance: String,
        @SerializedName("img_arr") val sortedStoreImgList: ArrayList<String>
)