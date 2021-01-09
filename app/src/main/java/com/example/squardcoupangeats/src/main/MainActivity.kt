package com.example.squardcoupangeats.src.main

import android.content.Context
import android.os.Bundle
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass.Companion.loginFlag
import com.example.squardcoupangeats.config.ApplicationClass.Companion.sSharedPreferences
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.config.XAccessTokenInterceptor
import com.example.squardcoupangeats.databinding.ActivityMainBinding
import com.example.squardcoupangeats.src.main.login.LoginRequestDialog
import com.example.squardcoupangeats.src.main.favorite.FavoriteFragment
import com.example.squardcoupangeats.src.main.home.HomeFragment
import com.example.squardcoupangeats.src.main.myEats.MyEatsFragment
import com.example.squardcoupangeats.src.main.payLog.PayLogFragment
import com.example.squardcoupangeats.src.main.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginFlag = sSharedPreferences.getInt("save login state", 0)

        val placeName : String
        if(intent.hasExtra("placeName")) {
            placeName = intent.getStringExtra("placeName").toString()
        } else {
            placeName = "배달 주소를 입력하세요."
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
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, FavoriteFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
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

}