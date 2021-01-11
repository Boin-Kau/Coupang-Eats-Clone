package com.example.squardcoupangeats.src.main.favorite

import android.os.Bundle
import android.util.Log
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityFavoriteBinding
import com.example.squardcoupangeats.src.main.favorite.models.GetFavoriteStoreResponse
import com.example.squardcoupangeats.src.main.favorite.service.FavoriteActivityView
import com.example.squardcoupangeats.src.main.favorite.service.FavoriteService

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>(ActivityFavoriteBinding::inflate), FavoriteActivityView {

    val TAG = "tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FavoriteService(this).tryGetFavoriteStores()
    }

    override fun onGetFavoriteStoreSuccess(response: GetFavoriteStoreResponse) {
        Log.d(TAG, "Get Favorite Store 성공 : ${response.message}")
    }

    override fun onGetFavoriteStoreFailure(message: String) {
        Log.d("오류", message)
    }
}