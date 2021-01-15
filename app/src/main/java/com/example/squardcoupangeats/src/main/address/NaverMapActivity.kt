package com.example.squardcoupangeats.src.main.address

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.ApplicationClass.Companion.LOCATION_PERMISSION_REQUEST_CODE
import com.example.squardcoupangeats.config.ApplicationClass.Companion.PERMISSIONS
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityNaverMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.widget.LocationButtonView
import java.util.jar.Manifest

@Suppress("DEPRECATION")
class NaverMapActivity : BaseActivity<ActivityNaverMapBinding>(ActivityNaverMapBinding::inflate), OnMapReadyCallback {
    private lateinit var locationSource: FusedLocationSource
    private lateinit var nMap: NaverMap
    private lateinit var mapFragment: MapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 상태바 색상 White로 변경 / 상태바 아이콘 어둡게 설정
        this.window?.apply {
            this.statusBarColor = Color.WHITE
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        val fm = supportFragmentManager
        mapFragment = fm.findFragmentById(R.id.frag_naver_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.frag_naver_map, it).commit()
            }

        mapFragment.getMapAsync(this)

        val lat = 37.614578
        val lon = 126.835075
        val placeName ="행신동리치빌1차"
        val placeAddress = "경기도 고양시 덕양구 용현로5번길 50"
        binding.naverMapPlaceName.text = placeName
        binding.naverMapPlaceAddress.text = placeAddress
    }

    override fun onMapReady(naverMap: NaverMap) {
        nMap = naverMap
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        nMap.locationSource = locationSource
        nMap.locationTrackingMode = LocationTrackingMode.Follow
        val uiSettings = nMap.uiSettings
        uiSettings.isZoomControlEnabled = false
        uiSettings.isLocationButtonEnabled = true

        val marker = Marker()

//        naverMap.addOnLocationChangeListener { location ->
//            val cameraPosition = CameraPosition(LatLng(location.latitude, location.longitude), 15.0)
//            nMap.moveCamera(CameraUpdate.toCameraPosition(cameraPosition))
//
//            marker.position = LatLng(location.latitude, location.longitude)
//            marker.icon = OverlayImage.fromResource(R.drawable.ic_marker)
//            marker.width = 150
//            marker.height = 150
//            marker.map = nMap
//        }

//        val locationOverlay = nMap.locationOverlay
//        locationOverlay.isVisible = true
//        locationOverlay.position = LatLng(37.364059, 126.500847)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)
                return
            }
        }
    }
}