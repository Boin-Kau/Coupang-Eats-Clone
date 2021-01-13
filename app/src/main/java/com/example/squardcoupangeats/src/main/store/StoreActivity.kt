package com.example.squardcoupangeats.src.main.store

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityStoreBinding
import com.example.squardcoupangeats.src.main.menu.MenuActivity
import com.example.squardcoupangeats.src.main.store.adapter.StoreMenuCategoryAdapter
import com.example.squardcoupangeats.src.main.store.adapter.StoreReviewAdapter
import com.example.squardcoupangeats.src.main.store.adapter.StoreTopImageAdapter
import com.example.squardcoupangeats.src.main.store.models.*
import com.example.squardcoupangeats.src.main.store.service.StoreActivityView
import com.example.squardcoupangeats.src.main.store.service.StoreService
import kotlinx.android.synthetic.main.activity_store.*
import java.util.*
import kotlin.collections.ArrayList


@Suppress("DEPRECATION")
class StoreActivity : BaseActivity<ActivityStoreBinding>(ActivityStoreBinding::inflate), StoreActivityView {

    val TAG = "tag"
    private var storeIndex : Int = 0
    private lateinit var timer : Timer
    private var tabCategoryList = mutableListOf<String>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.hasExtra("storeIndex")) {
            storeIndex = intent.getIntExtra("storeIndex", 0)
            StoreService(this).tryGetSpecificStores(storeIndex)
        }

        this.window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = Color.TRANSPARENT
        }

        // 툴바 생성
        setSupportActionBar(binding.storeActivityToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar!!.title = ""
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_store_activity_tool_bar, menu)
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
                showCustomToast("즐겨찾기 등록")
                StoreService(this).tryPostFavoriteStore(PostFavoriteStoreRequest(storeIdx = storeIndex))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onGetSpecificStoreSuccess(response: SpecificStoreResponse) {
        Log.d(TAG, "Get Specific Store 성공 : ${response.message}")

        val imageList = response.storePhoto
        val storeInfo = response.storeInfo
        val photoReview = response.photoReview
        val categoryMenu = response.categoryMenu
        if(response.couponInfo != null) {
            val couponInfo = response.couponInfo
            setCouponInfo(couponInfo)
        } else {
            binding.storeActivityGetCouponBtn.visibility = View.GONE
        }
        setTogImageAdapter(imageList)
        setStoreInfo(storeInfo)
        setPhotoReview(photoReview)
        setMenu(categoryMenu)


    }

    override fun onGetSpecificStoreFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("오류", message)
    }

    override fun onPostFavoriteStoreSuccess(response: FavoriteStoreResponse) {
        Log.d(TAG, "Post Favorite 성공 : ${response.message}")
    }

    override fun onPostFavoriteStoreFailure(message: String) {
        Log.d(TAG, "오류 : $message")
    }

    private fun setTogImageAdapter(imageList: ArrayList<String>) {
        val storeTopImageAdapter = StoreTopImageAdapter(imageList)
        binding.storeActivityTopBarViewPager.apply {
            adapter = storeTopImageAdapter
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


    private fun setCouponInfo(couponInfo: ResultCouponInfo) {
        binding.storeActivitySortOfCouponTv.text = couponInfo.coupon
    }


    @SuppressLint("SetTextI18n")
    private fun setStoreInfo(storeInfo: ArrayList<ResultStoreInfo>) {
        binding.storeActivityPlaceName.text = storeInfo[0].storeName
        binding.storeActivityDeliveryTimeTv.text = storeInfo[0].deliveryTime
        binding.storeActivityDeliveryFeeTv.text = storeInfo[0].deliveryTime
        binding.storeActivityMinimumOfDeliveryPay.text = storeInfo[0].minOrderCost

        if(storeInfo[0].reviewCount == 0) {
            binding.storeActivityRatingAndReviewLayout.visibility = View.GONE
        } else {
            binding.storeActivityStarCount.text = storeInfo[0].storeStar.toString()
            binding.storeActivityReviewCountTv.text = "리뷰 " + storeInfo[0].reviewCount.toString() + "개"
        }
    }

    private fun setPhotoReview(photoReview: ArrayList<ResultPhotoReview>) {
        if(photoReview.size != 0) {
            val storeReviewAdapter = StoreReviewAdapter(photoReview)
            binding.storeActivityReviewRecyclerview.adapter = storeReviewAdapter
            binding.storeActivityReviewRecyclerview.layoutManager = LinearLayoutManager(this).also { it.orientation = LinearLayoutManager.HORIZONTAL}
        } else {
            binding.storeActivityReviewRecyclerview.visibility = View.GONE
        }
    }

    private fun setMenu(categoryMenu: ArrayList<ResultCategoryMenu>) {
        val tabLayout = binding.storeActivityContentLayout.storeActivityContentTabLayout
        val contentLayout = binding.storeActivityContentLayout

        // 탭 Item 추가를 위해 탭 이름
        for(category in categoryMenu) {
            tabCategoryList.add(category.categoryName)
        }
        // 탭 Item 설정
        for(tabCategory in tabCategoryList) {
            tabLayout.addTab(tabLayout.newTab().setText(tabCategory))
        }
        if(categoryMenu.size > 0) {
            contentLayout.storeActivityContentCategoryName1.text = categoryMenu[0].categoryName
            if(categoryMenu[0].categoryDetail != null) {
                contentLayout.storeActivityContentCategoryDetail1.text = categoryMenu[0].categoryDetail
            } else {
                contentLayout.storeActivityContentCategoryDetail1.visibility = View.GONE
            }
            val menuCategoryAdapter1 = StoreMenuCategoryAdapter(categoryMenu[0].menuList)
            contentLayout.storeActivityContentCategoryRecyclerview1.adapter = menuCategoryAdapter1
            menuCategoryAdapter1.menuCategoryItemClick = object : StoreMenuCategoryAdapter.MenuCategoryItemClick {
                override fun onClick(view: View, position: Int) {
                    val intent = Intent(this@StoreActivity, MenuActivity::class.java)
                    intent.putExtra("menuIndex", categoryMenu[0].menuList[position].menuIdx)
                    startActivity(intent)
                }
            }
        }
        if (categoryMenu.size > 1) {
            contentLayout.storeActivityContentCategoryName2.text = categoryMenu[1].categoryName
            if(categoryMenu[1].categoryDetail != null) {
                contentLayout.storeActivityContentCategoryDetail2.text = categoryMenu[1].categoryDetail
            } else {
                contentLayout.storeActivityContentCategoryDetail2.visibility = View.GONE
            }
            val menuCategoryAdapter2 = StoreMenuCategoryAdapter(categoryMenu[1].menuList)
            contentLayout.storeActivityContentCategoryRecyclerview2.adapter = menuCategoryAdapter2
            menuCategoryAdapter2.menuCategoryItemClick = object : StoreMenuCategoryAdapter.MenuCategoryItemClick {
                override fun onClick(view: View, position: Int) {
                    val intent = Intent(this@StoreActivity, MenuActivity::class.java)
                    intent.putExtra("menuIndex", categoryMenu[1].menuList[position].menuIdx)
                    startActivity(intent)
                }
            }
        }
        if (categoryMenu.size > 2) {
            contentLayout.storeActivityContentCategoryName3.text = categoryMenu[2].categoryName
            if(categoryMenu[2].categoryDetail != null) {
                contentLayout.storeActivityContentCategoryDetail3.text = categoryMenu[2].categoryDetail
            } else {
                contentLayout.storeActivityContentCategoryDetail3.visibility = View.GONE
            }
            val menuCategoryAdapter3 = StoreMenuCategoryAdapter(categoryMenu[2].menuList)
            contentLayout.storeActivityContentCategoryRecyclerview3.adapter = menuCategoryAdapter3
            menuCategoryAdapter3.menuCategoryItemClick = object : StoreMenuCategoryAdapter.MenuCategoryItemClick {
                override fun onClick(view: View, position: Int) {
                    val intent = Intent(this@StoreActivity, MenuActivity::class.java)
                    intent.putExtra("menuIndex", categoryMenu[2].menuList[position].menuIdx)
                    startActivity(intent)
                }
            }
        }
        if (categoryMenu.size > 3) {
            contentLayout.storeActivityContentCategoryName4.text = categoryMenu[3].categoryName
            if(categoryMenu[3].categoryDetail != null) {
                contentLayout.storeActivityContentCategoryDetail4.text = categoryMenu[3].categoryDetail
            } else {
                contentLayout.storeActivityContentCategoryDetail4.visibility = View.GONE
            }
            val menuCategoryAdapter4 = StoreMenuCategoryAdapter(categoryMenu[3].menuList)
            contentLayout.storeActivityContentCategoryRecyclerview4.adapter = menuCategoryAdapter4
            menuCategoryAdapter4.menuCategoryItemClick = object : StoreMenuCategoryAdapter.MenuCategoryItemClick {
                override fun onClick(view: View, position: Int) {
                    val intent = Intent(this@StoreActivity, MenuActivity::class.java)
                    intent.putExtra("menuIndex", categoryMenu[3].menuList[position].menuIdx)
                    startActivity(intent)
                }
            }
        }
        if (categoryMenu.size > 4) {
            contentLayout.storeActivityContentCategoryName5.text = categoryMenu[4].categoryName
            if(categoryMenu[4].categoryDetail != null) {
                contentLayout.storeActivityContentCategoryDetail5.text = categoryMenu[4].categoryDetail
            } else {
                contentLayout.storeActivityContentCategoryDetail5.visibility = View.GONE
            }
            val menuCategoryAdapter5 = StoreMenuCategoryAdapter(categoryMenu[4].menuList)
            contentLayout.storeActivityContentCategoryRecyclerview5.adapter = menuCategoryAdapter5
            menuCategoryAdapter5.menuCategoryItemClick = object : StoreMenuCategoryAdapter.MenuCategoryItemClick {
                override fun onClick(view: View, position: Int) {
                    val intent = Intent(this@StoreActivity, MenuActivity::class.java)
                    intent.putExtra("menuIndex", categoryMenu[4].menuList[position].menuIdx)
                    startActivity(intent)
                }
            }
        } else {
            contentLayout.menuCategory5Layout.visibility = View.GONE
            contentLayout.storeActivityContentCategoryRecyclerview5.visibility = View.GONE
        }
    }
}