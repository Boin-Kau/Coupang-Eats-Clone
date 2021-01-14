package com.example.squardcoupangeats.src.main.menu.models

import com.example.squardcoupangeats.src.main.store.models.MenuListData
import com.example.squardcoupangeats.src.main.store.models.ResultStoreInfo
import com.google.gson.annotations.SerializedName

data class SpecificMenuResponse(
    @SerializedName("menuPhoto") val menuPhoto : ArrayList<String>,
    @SerializedName("menuInfo") val menuInfo : ArrayList<ResultMenuInfo>,
    @SerializedName("optCategoryMenu") val optCategoryMenu : ArrayList<ResultOptCategoryMenu>,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)

data class ResultMenuInfo(
    @SerializedName("menuIdx") val menuIdx : Int,
    @SerializedName("menuName") val menuName : String,
    @SerializedName("menuDetail") val menuDetail : String,
    @SerializedName("menuPrice") val menuPrice : Int
)

data class ResultOptCategoryMenu(
    @SerializedName("optCategoryIdx") val optCategoryIdx : Int,
    @SerializedName("optCategoryName") val optCategoryName : String,
    @SerializedName("isMandatory") val isMandatory : String,
    @SerializedName("maxSelect") val maxSelect : Int,
    @SerializedName("optmenuList") val optMenuList : ArrayList<OptMenuListData>
)

data class OptMenuListData(
    @SerializedName("menuOptIdx") val menuOptIdx : Int,
    @SerializedName("menuOptName") val menuOptName : String,
    @SerializedName("menuOptPrice") val menuOptPrice : String,
    @SerializedName("optPrice") val optPrice : Int
)
