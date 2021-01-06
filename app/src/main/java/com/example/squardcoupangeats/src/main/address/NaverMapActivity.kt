package com.example.squardcoupangeats.src.main.address

import android.os.Bundle
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityNaverMapBinding
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

class NaverMapActivity : BaseActivity<ActivityNaverMapBinding>(ActivityNaverMapBinding::inflate), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.frag_naver_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.frag_naver_map, it).commit()
            }

        mapFragment.getMapAsync(this)


    }

    override fun onMapReady(naverMap: NaverMap) {

    }
}