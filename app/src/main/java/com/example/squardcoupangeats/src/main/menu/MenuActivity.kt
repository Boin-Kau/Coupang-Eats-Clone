package com.example.squardcoupangeats.src.main.menu

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityMenuBinding
import com.example.squardcoupangeats.src.main.menu.adapter.MenuTopImageAdapter
import com.example.squardcoupangeats.src.main.menu.models.ResultMenuInfo
import com.example.squardcoupangeats.src.main.menu.models.ResultOptCategoryMenu
import com.example.squardcoupangeats.src.main.menu.models.SpecificMenuResponse
import com.example.squardcoupangeats.src.main.menu.service.MenuActivityView
import com.example.squardcoupangeats.src.main.menu.service.MenuService
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class MenuActivity : BaseActivity<ActivityMenuBinding>(ActivityMenuBinding::inflate), MenuActivityView {

    val TAG = "tag"
    private var menuIndex : Int = 0
    private lateinit var timer : Timer
    var menuAmount = 1

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
        setSupportActionBar(binding.menuActivityToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar!!.title = "" // 메뉴이름으로 세팅

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_menu_activity_tool_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menu_menu_share -> {
                showCustomToast("share")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onGetSpecificMenuSuccess(response: SpecificMenuResponse) {
        Log.d(TAG, response.message)

        val imageList = response.menuPhoto
        val infoList = response.menuInfo
        val categoryList = response.optCategoryMenu

        setTogImageAdapter(imageList)
        setMenuInfo(infoList, categoryList)
    }

    private fun setMenuInfo(infoList: ArrayList<ResultMenuInfo>, categoryList: ArrayList<ResultOptCategoryMenu>) {
        val contentLayout = binding.menuActivityContentLayout
        contentLayout.menuActivityMenuName.text = infoList[0].menuName
        contentLayout.menuActivityMenuDetail.text = infoList[0].menuDetail
        // contentLayout.menuActivityMenuPrice.text = infoList[0].menuPrice
        contentLayout.menuActivityMenuAmount.text = menuAmount.toString()

//        contentLayout.menuActivityAddCount.setOnClickListener {
//            menuAmount++
//            contentLayout.menuActivityMenuAmount.text = menuAmount.toString()
//            contentLayout.menuActivityMenuPrice.text = (infoList[0].menuPrice.toInt() * menuAmount).toString()
//        }
//        contentLayout.menuActivityRemoveCount.setOnClickListener {
//            if(menuAmount > 1) {
//                menuAmount--
//                contentLayout.menuActivityMenuAmount.text = menuAmount.toString()
//                contentLayout.menuActivityMenuPrice.text = (infoList[0].menuPrice.toInt() * menuAmount).toString()
//            }
//        }


    }

    private fun setTogImageAdapter(imageList: ArrayList<String>) {

        val menuTopImageAdapter = MenuTopImageAdapter(imageList)
        binding.menuActivityTopBarViewPager.apply {
            adapter = menuTopImageAdapter
            var currentPage = 0
            val handler = Handler()
            val update = Runnable {
                if(currentPage == 3) {
                    currentPage = 0
                }
                setCurrentItem(currentPage++, true)
            }
            timer = Timer()
            timer.schedule(object : TimerTask(){
                override fun run() {
                    handler.post(update)
                }

            }, 500, 3000)
        }

    }

    override fun onGetSpecificMenuFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("오류", message)
    }
}