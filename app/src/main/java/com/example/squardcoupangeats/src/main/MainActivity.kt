package com.example.squardcoupangeats.src.main

import android.os.Bundle
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
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

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
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
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, PayLogFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_my_eats -> {

                        // 로그인 요청 Dialog
                        LoginRequestDialog(this).show()

                        val loginFlag = 0
                        if(loginFlag == 1) {
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.main_frm, MyEatsFragment())
                                    .commitAllowingStateLoss()
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                }
                false
            })
    }
}