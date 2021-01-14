package com.example.squardcoupangeats.config

import android.app.Application
import android.content.SharedPreferences
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.src.main.address.adapter.SearchedAddressData
import com.kakao.sdk.common.KakaoSdk
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import java.util.jar.Manifest

class ApplicationClass : Application() {

    // 테스트 서버 주소
    val API_URL = "https://prod.coupang-eats.shop"

    // 실 서버 주소
    // val API_URL = "http://api.test.com/"

    // https://coupang-eats.shop 이게 메인이고
    // https://prod.coupang-eats.shop
    // https://test.coupang-eats.shop 이게 서브도메인입니다!

    // 카카오 키워드로 주소 검색 api URL
    val KAKAO_BASE_URL = "https://dapi.kakao.com/"

    // 코틀린의 전역변수 문법
    companion object {
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var sSharedPreferences: SharedPreferences

        // JWT Token Header 키 값
        val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"

        // Retrofit 인스턴스, 앱 실행시 한번만 생성하여 사용합니다.
        lateinit var sRetrofit: Retrofit

        //
        lateinit var naverGeoRetrofit: Retrofit

        // 카테고리 이미지 리스트(임시)
        val categoryNameList = mutableListOf<String>()
        val categoryImageList = arrayListOf(R.drawable.ic_category_img_1, R.drawable.ic_category_img_2,
                R.drawable.ic_category_img_3, R.drawable.ic_category_img_4, R.drawable.ic_category_img_5,
                R.drawable.ic_category_img_6, R.drawable.ic_category_img_7, R.drawable.ic_category_img_8,
                R.drawable.ic_category_img_9, R.drawable.ic_category_img_10, R.drawable.ic_category_img_11,
                R.drawable.ic_category_img_12, R.drawable.ic_category_img_13, R.drawable.ic_category_img_14,
                R.drawable.ic_category_img_15, R.drawable.ic_category_img_16, R.drawable.ic_category_img_17,
                R.drawable.ic_category_img_18, R.drawable.ic_category_img_19, R.drawable.ic_category_img_20,
                R.drawable.ic_category_img_21, R.drawable.ic_category_img_22, R.drawable.ic_category_img_23, R.drawable.ic_category_img_24)

        // 네이버 지도 Request Code
        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
        val PERMISSIONS = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)

        // 네이버 로그인
        const val OAUTH_CLIENT_ID = "rcTc1xg0lEho9eq9KQ9O"
        const val OAUTH_CLIENT_SECRET = "JFWdI7zZDu"
        const val OAUTH_CLIENT_NAME = "SquardCoupangEats"

        // 네이버 Api
        const val NAVER_API_CLIENT_ID = "dndqrrt849"
        const val NAVER_API_CLIENT_SECRET = "GMfbgoShnSHIk2VoqkWLkvgnehXbOxhAjB7r7oKO"

        // 카카오 키워드로 주소 검색 native key
        const val KAKAO_REST_API_APP_KEY = "4f2bb9746ab758b7442d9739900da43d"

        // 부트페이 Application ID
        const val BOOT_PAY_APPLICATION_ID = "600015b55b294800202a1c03"

        // 로그인 상태 확인 flag
        var loginFlag : Int = 0

        // 저장한 주소 List
        var searchedAddressList = mutableListOf<SearchedAddressData>()

        // 라디오 버튼 flag
        var radioBtnSelected1 = -1
        var radioBtnSelected2 = -1

    }

    // 앱이 처음 생성되는 순간, SP를 새로 만들어주고, 레트로핏 인스턴스를 생성합니다.
    override fun onCreate() {
        super.onCreate()
        sSharedPreferences =
            applicationContext.getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE)

        // 카카오 로그인
        KakaoSdk.init(this, "e69718bae3a089e5903837af214a344c")

        // 레트로핏 인스턴스 생성
        initRetrofitInstance()
    }

    // 레트로핏 인스턴스를 생성하고, 레트로핏에 각종 설정값들을 지정해줍니다.
    // 연결 타임아웃시간은 5초로 지정이 되어있고, HttpLoggingInterceptor를 붙여서 어떤 요청이 나가고 들어오는지를 보여줍니다.


    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .addInterceptor(HttpLoggingInterceptor()) // API Response 로그 작성용
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()

        // sRetrofit 이라는 전역변수에 API url, 인터셉터, Gson을 넣어주고 빌드해주는 코드
        // 이 전역변수로 http 요청을 서버로 보내면 됩니다.
        sRetrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        naverGeoRetrofit = Retrofit.Builder()
                .baseUrl(KAKAO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}