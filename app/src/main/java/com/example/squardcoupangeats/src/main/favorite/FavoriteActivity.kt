package com.example.squardcoupangeats.src.main.favorite

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityFavoriteBinding
import com.example.squardcoupangeats.src.main.favorite.adapter.FavoriteStoreAdapter
import com.example.squardcoupangeats.src.main.favorite.models.GetFavoriteStoreResponse
import com.example.squardcoupangeats.src.main.favorite.models.ResultHartStore
import com.example.squardcoupangeats.src.main.favorite.service.FavoriteActivityView
import com.example.squardcoupangeats.src.main.favorite.service.FavoriteService
import com.example.squardcoupangeats.src.main.store.StoreActivity

@Suppress("DEPRECATION")
class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>(ActivityFavoriteBinding::inflate), FavoriteActivityView {

    val TAG = "tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FavoriteService(this).tryGetFavoriteStores()

        // 상태바 설정
        this.window?.apply {
            this.statusBarColor = Color.WHITE
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // 툴바 생성
        setSupportActionBar(binding.favoriteActivityToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.title = "즐겨찾기"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_favorite_activity_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite_activity_toolbar_update_btn -> {
                showCustomToast("update")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onGetFavoriteStoreSuccess(response: GetFavoriteStoreResponse) {
        Log.d(TAG, "Get Favorite Store 성공 : ${response.message}")

        binding.favoriteActivityTotalFavoriteNum.text = "총 "+response.heartCount.toString()+"개"
        setAdapter(response.heartStore)
    }

    private fun setAdapter(heartStoreList: ArrayList<ResultHartStore>) {
        val favoriteAdapter = FavoriteStoreAdapter(heartStoreList)
        binding.favoriteActivityRecyclerview.adapter = favoriteAdapter
        favoriteAdapter.favoriteStoreItemClick = object : FavoriteStoreAdapter.FavoriteStoreItemClick{
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@FavoriteActivity, StoreActivity::class.java)
                intent.putExtra("storeIndex", heartStoreList[position].storeIdx)
                startActivity(intent)
            }

        }

    }

    override fun onGetFavoriteStoreFailure(message: String) {
        Log.d("오류", message)
    }
}