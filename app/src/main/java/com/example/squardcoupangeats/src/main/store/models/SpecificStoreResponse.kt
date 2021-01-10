package com.example.squardcoupangeats.src.main.store.models

import com.google.gson.annotations.SerializedName

data class SpecificStoreResponse(
    @SerializedName("storePhoto") val storePhoto : ArrayList<String>,
    @SerializedName("storeInfo") val storeInfo : ArrayList<ResultStoreInfo>,
    @SerializedName("photoReview") val photoReview : ArrayList<ResultPhotoReview>,
    @SerializedName("categoryMenu") val categoryMenu : ArrayList<ResultCategoryMenu>,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class ResultStoreInfo(
    @SerializedName("storeName") val storeName : String,
    @SerializedName("storeStar") val storeStar : Double,
    @SerializedName("deliveryTime") val deliveryTime : String,
    @SerializedName("deliveryFee") val deliveryFee : String,
    @SerializedName("minOrderCost") val minOrderCost : String,
    @SerializedName("reviewCount") val reviewCount : Int
)

data class ResultPhotoReview(
    @SerializedName("reviewIdx") val reviewIdx : Int,
    @SerializedName("content") val content : String,
    @SerializedName("reviewStar") val reviewStar : Int,
    @SerializedName("reviewPhoto") val reviewPhoto : String
)

data class ResultCategoryMenu(
    @SerializedName("categoryIdx") val categoryIdx : Int,
    @SerializedName("categoryName") val categoryName : String,
    @SerializedName("categoryDetail") val categoryDetail : String,
    @SerializedName("menuList") val menuList : ArrayList<MenuListData>
)

data class MenuListData(
    @SerializedName("menuIdx") val menuIdx : Int,
    @SerializedName("menuname") val menuName : String,
    @SerializedName("menuDetail") val menuDetail : String,
    @SerializedName("menuPrice") val menuPrice : String,
    @SerializedName("menuThumbnail") val menuThumbnail : String
)