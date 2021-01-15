package com.example.squardcoupangeats.src.main.myEats

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivitySettingBinding
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.list_store_activity_menu_category_recyclerview.*

class SettingActivity : BaseActivity<ActivitySettingBinding>(ActivitySettingBinding::inflate) {
    val TAG = "tag"
    private val edit = ApplicationClass.sSharedPreferences.edit()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.window?.apply {
            this.statusBarColor = Color.WHITE
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        binding.settingActivityLogoutBtn.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("정말로 로그아웃 하시겠습니까?")
            .setPositiveButton("로그아웃") { dialog, which ->
                UserApiClient.instance.logout { error ->
                    if (error != null) {
                        Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                    }
                    else {
                        Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                        ApplicationClass.loginFlag = 0
                        edit.putInt("save login state", ApplicationClass.loginFlag).apply()
                        finish()
                    }
                }
            }
            .setNeutralButton("취소", null)
            .create()
        alertDialog.setView(view)
        alertDialog.show()
    }
}