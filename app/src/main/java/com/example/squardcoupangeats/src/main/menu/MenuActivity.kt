package com.example.squardcoupangeats.src.main.menu

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityMenuBinding
import com.example.squardcoupangeats.src.main.menu.models.SpecificMenuResponse
import com.example.squardcoupangeats.src.main.menu.service.MenuActivityView
import com.example.squardcoupangeats.src.main.menu.service.MenuService
import com.example.squardcoupangeats.src.main.store.service.StoreService

@Suppress("DEPRECATION")
class MenuActivity : BaseActivity<ActivityMenuBinding>(ActivityMenuBinding::inflate), MenuActivityView {

    val TAG = "tag"
    private var menuIndex : Int = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Store Activity Recyclerview에 클릭 이벤트가 발생하면
        // 해당 position의 index를 intent에 담아 Menu Activity로 startActivity
        // Menu Activity에서는 hasExtra로 menuIndex를 키값으로 가지는 value를 꺼내, MenuService의 메소드로 index값을 파라미터로 넘겨줌

        if(intent.hasExtra("menuIndex")) {
            menuIndex = intent.getIntExtra("menuIndex", 0)
            MenuService(this).tryGetSpecificMenus(menuIndex)
        }

        this.window?.apply {
            this.statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // 툴바 생성
        //setSupportActionBar(binding.storeActivityToolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
//        supportActionBar!!.title = ""

    }

    override fun onGetSpecificMenuSuccess(response: SpecificMenuResponse) {
        Log.d(TAG, response.message)
    }

    override fun onGetSpecificMenuFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("오류", message)
    }
}