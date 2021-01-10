package com.example.squardcoupangeats.src.main.store

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityStoreBinding
import com.example.squardcoupangeats.src.main.store.models.SpecificStoreResponse
import com.example.squardcoupangeats.src.main.store.service.StoreActivityView
import com.example.squardcoupangeats.src.main.store.service.StoreService

@Suppress("DEPRECATION")
class StoreActivity : BaseActivity<ActivityStoreBinding>(ActivityStoreBinding::inflate), StoreActivityView {

    val TAG = "tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val index : Int
        if(intent.hasExtra("index")) {
            index = intent.getIntExtra("index", 0)
            StoreService(this).tryGetSpecificStores(index)
        }

        this.window?.apply {
            this.statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // 툴바 생성
        setSupportActionBar(binding.storeActivityToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar!!.title = ""

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_store, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.menu_store_share -> {
                showCustomToast("share")
                true
            }
            R.id.menu_store_favorite -> {
                showCustomToast("favorite")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onGetSpecificStoreSuccess(response: SpecificStoreResponse) {
        showCustomToast("Specific Store 성공 : $response.message")
        Log.d(TAG, response.message)

    }

    override fun onGetSpecificStoreFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("오류", message)
    }
}