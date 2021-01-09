package com.example.squardcoupangeats.src.main.address

import android.os.Bundle
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

class NaverMapActivity : BaseActivity<ActivityNaverMapBinding>(ActivityNaverMapBinding::inflate), OnMapReadyCallback {
    private lateinit var locationSource: FusedLocationSource
    private lateinit var nMap: NaverMap
    private lateinit var mapFragment: MapFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fm = supportFragmentManager
        mapFragment = fm.findFragmentById(R.id.frag_naver_map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.frag_naver_map, it).commit()
            }

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {
        nMap = naverMap
        val marker = Marker()
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
        nMap.locationSource = locationSource
        nMap.locationTrackingMode = LocationTrackingMode.Follow



        naverMap.addOnLocationChangeListener { location ->
            val cameraPosition = CameraPosition(LatLng(location.latitude, location.longitude), 15.0)
            nMap.moveCamera(CameraUpdate.toCameraPosition(cameraPosition))

            marker.position = LatLng(location.latitude, location.longitude)
            marker.icon = OverlayImage.fromResource(R.drawable.ic_marker)
            marker.width = 150
            marker.height = 150
            marker.map = nMap
        }

//        val locationOverlay = nMap.locationOverlay
//        locationOverlay.isVisible = true
//        locationOverlay.position = LatLng(37.364059, 126.500847)

        val uiSettings = nMap.uiSettings
        uiSettings.isZoomControlEnabled = false
        uiSettings.isLocationButtonEnabled = true

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