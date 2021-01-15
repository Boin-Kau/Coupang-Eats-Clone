package com.example.squardcoupangeats.src.main

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.ApplicationClass.Companion.loginFlag
import com.example.squardcoupangeats.config.ApplicationClass.Companion.sSharedPreferences
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityMainBinding
import com.example.squardcoupangeats.src.main.coord.ReverseGeoResponse
import com.example.squardcoupangeats.src.main.coord.ReverseGeoService
import com.example.squardcoupangeats.src.main.coord.ReverseGeoView
import com.example.squardcoupangeats.src.main.favorite.FavoriteActivity
import com.example.squardcoupangeats.src.main.login.LoginRequestDialog
import com.example.squardcoupangeats.src.main.home.HomeFragment
import com.example.squardcoupangeats.src.main.myEats.MyEatsFragment
import com.example.squardcoupangeats.src.main.payLog.PayLogFragment
import com.example.squardcoupangeats.src.main.search.SearchFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.util.FusedLocationSource

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), ReverseGeoView {

    val TAG = "tag"
    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window?.apply {
            this.statusBarColor = Color.WHITE
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // 사용자 위치 찾기
        initLocation()
        val coords = "$longitude,$latitude"

        // 위,경도 값에 해당하는 주소 구하기
        //ReverseGeoService(this).tryGetReverseGeo(coords = coords)

        loginFlag = sSharedPreferences.getInt("save login state", 0)
        val jwtCheck = sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN, null)
        if (jwtCheck != null) {
            Log.d(TAG, jwtCheck)
        }

        val placeName : String
        if(intent.hasExtra("placeName")) {
            placeName = intent.getStringExtra("placeName").toString()
        } else {
            placeName = "행신동리치빌1차"
        }
        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment(placeName)).commitAllowingStateLoss()



        binding.mainBtmNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment(placeName))
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_search -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, SearchFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_favorite -> {
                        startActivity(Intent(this, FavoriteActivity::class.java))
                    }
                    R.id.menu_main_btm_nav_pay_log -> {
                        when(loginFlag) {
                            1 -> {
                                supportFragmentManager.beginTransaction()
                                        .replace(R.id.main_frm, PayLogFragment())
                                        .commitAllowingStateLoss()
                                return@OnNavigationItemSelectedListener true
                            }
                            0 -> LoginRequestDialog(this, this).show()
                        }
                    }
                    R.id.menu_main_btm_nav_my_eats -> {
                        when(loginFlag) {
                            1 -> {
                                supportFragmentManager.beginTransaction()
                                        .replace(R.id.main_frm, MyEatsFragment())
                                        .commitAllowingStateLoss()
                                return@OnNavigationItemSelectedListener true
                            }
                            0 -> LoginRequestDialog(this, this).show()
                        }
                    }
                }
                false
            })
    }

    private fun initLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if(location == null) {
                    Log.e(TAG, "location get fail")
                } else {
                    Log.d(TAG, "${location.latitude} , ${location.longitude}")
                    latitude = location.latitude
                    longitude = location.longitude
                }
            }
            .addOnFailureListener {
                Log.e(TAG, "location error is ${it.message}")
                it.printStackTrace()
            }
    }

    override fun onGetReverseGeoSuccess(response: ReverseGeoResponse) {
        Log.d(TAG, "Reverse Geo 성공 : ${response.status.message}")
    }

    override fun onGetReverseGeoFailure(message: String) {
        Log.d(TAG, "오류 : $message")
    }
}