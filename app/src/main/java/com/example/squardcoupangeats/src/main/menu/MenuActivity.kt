package com.example.squardcoupangeats.src.main.menu

import android.annotation.SuppressLint
import android.content.Intent
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
import com.example.squardcoupangeats.src.main.menu.adapter.CheckBoxState
import com.example.squardcoupangeats.src.main.menu.adapter.MenuOptionAdapter
import com.example.squardcoupangeats.src.main.menu.adapter.MenuOptionCheckboxAdapter
import com.example.squardcoupangeats.src.main.menu.adapter.MenuTopImageAdapter
import com.example.squardcoupangeats.src.main.menu.models.*
import com.example.squardcoupangeats.src.main.menu.service.MenuActivityView
import com.example.squardcoupangeats.src.main.menu.service.MenuService
import com.example.squardcoupangeats.src.main.store.StoreActivity
import java.util.*

@Suppress("DEPRECATION")
class MenuActivity : BaseActivity<ActivityMenuBinding>(ActivityMenuBinding::inflate), MenuActivityView {

    private var price = 0
    val TAG = "tag"
    private var menuIndex : Int = 0
    private lateinit var timer : Timer
    var menuAmount = 1
    var checkBoxStateList = ArrayList<CheckBoxState>()

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

        for(i in 0..30) {
            checkBoxStateList.add(CheckBoxState(false))
        }

        // 카트 담기
        binding.menuActivityAddCart.setOnClickListener {
            MenuService(this).tryPostAddingCart(PostAddingCartRequest(storeIdx = 1, menuIdx = 1, quantity = 1, optionList = arrayListOf(1,5,7)))
            // Intent에 총 금액 담기
            val intent = Intent(this, StoreActivity::class.java)
            intent.putExtra("cart", 30000)
            intent.putExtra("storeIndex", 1)
            startActivity(intent)
            finish()
        }
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
        setMenuInfo(infoList)
        setOptionMenu(categoryList)
    }

    private fun setOptionMenu(categoryList: ArrayList<ResultOptCategoryMenu>) {
        val contentLayout = binding.menuActivityContentLayout

        val firstOptionList = categoryList[0]
        contentLayout.menuActivityFirstOptionName.text = firstOptionList.optCategoryName
        if(firstOptionList.isMandatory == "Y") {
            val firstOptionAdapter = MenuOptionAdapter(firstOptionList.optMenuList)
            contentLayout.menuActivityFirstOptionRecyclerview.adapter = firstOptionAdapter
            firstOptionAdapter.menuOptionItemClick = object : MenuOptionAdapter.MenuOptionItemClick {
                override fun onClick(view: View, position: Int) {
                    if(firstOptionList.optMenuList[position].optPrice != null) {
                        val currentPrice = contentLayout.menuActivityMenuPrice.text.toString()
                        contentLayout.menuActivityMenuPrice.text = (currentPrice.toInt() + firstOptionList.optMenuList[position].optPrice).toString()
                    }
                }
            }
        } else {
            val firstOptionAdapter = MenuOptionCheckboxAdapter(firstOptionList.optMenuList, checkBoxStateList)
            contentLayout.menuActivityFirstOptionRecyclerview.adapter = firstOptionAdapter
            contentLayout.menuActivityFirstOptionMandatory.visibility = View.GONE
        }

        val secondOptionList = categoryList[1]
        contentLayout.menuActivitySecondOptionName.text = secondOptionList.optCategoryName
        if(secondOptionList.isMandatory == "Y") {
            val secondOptionAdapter = MenuOptionAdapter(secondOptionList.optMenuList)
            contentLayout.menuActivitySecondOptionRecyclerview.adapter = secondOptionAdapter
        } else {
            val secondOptionAdapter = MenuOptionCheckboxAdapter(secondOptionList.optMenuList, checkBoxStateList)
            contentLayout.menuActivitySecondOptionRecyclerview.adapter = secondOptionAdapter
            contentLayout.menuActivitySecondOptionMandatory.visibility = View.GONE
        }

        val thirdOptionList = categoryList[2]
        contentLayout.menuActivityThirdOptionName.text = thirdOptionList.optCategoryName
        if(thirdOptionList.isMandatory == "Y") {
            val thirdOptionAdapter = MenuOptionAdapter(thirdOptionList.optMenuList)
            contentLayout.menuActivityThirdOptionRecyclerview.adapter = thirdOptionAdapter
        } else {
            val thirdOptionAdapter = MenuOptionCheckboxAdapter(thirdOptionList.optMenuList, checkBoxStateList)
            contentLayout.menuActivityThirdOptionRecyclerview.adapter = thirdOptionAdapter
            contentLayout.menuActivityThirdOptionMandatory.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setMenuInfo(infoList: ArrayList<ResultMenuInfo>){
        val contentLayout = binding.menuActivityContentLayout
        contentLayout.menuActivityMenuName.text = infoList[0].menuName
        contentLayout.menuActivityMenuDetail.text = infoList[0].menuDetail
        contentLayout.menuActivityMenuPrice.text = infoList[0].menuPrice.toString()
        contentLayout.menuActivityMenuAmount.text = menuAmount.toString()
        price = infoList[0].menuPrice

        contentLayout.menuActivityAddCount.setOnClickListener {
            val currentPrice = contentLayout.menuActivityMenuPrice.text.toString().toInt()
            contentLayout.menuActivityMenuPrice.text = (currentPrice + (currentPrice / menuAmount)).toString()
            menuAmount++
            contentLayout.menuActivityMenuAmount.text = menuAmount.toString()
        }

        contentLayout.menuActivityRemoveCount.setOnClickListener {
            if(menuAmount > 1) {
                val currentPrice = contentLayout.menuActivityMenuPrice.text.toString().toInt()
                contentLayout.menuActivityMenuPrice.text = (currentPrice - (currentPrice / menuAmount)).toString()
                menuAmount--
                contentLayout.menuActivityMenuAmount.text = menuAmount.toString()
            }
        }
    }

    private fun setTogImageAdapter(imageList: ArrayList<String>) {

        val menuTopImageAdapter = MenuTopImageAdapter(imageList)
        binding.menuActivityTopBarViewPager.apply {
            adapter = menuTopImageAdapter
            var currentPage = 0
            val handler = Handler()
            val update = Runnable {
                if(currentPage == imageList.size) {
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

    override fun onPostAddingCartSuccess(response: AddingCartResponse) {
        Log.d(TAG, response.message)
    }

    override fun onPostAddingCartFailure(message: String) {
        Log.d("오류", message)
    }

}