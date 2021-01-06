package com.example.squardcoupangeats.src.main.login

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.databinding.DialogLoginRequestBinding
import com.kakao.sdk.auth.LoginClient

class LoginRequestDialog(context: Context) : Dialog(context) {

    val TAG = "tag"

    private lateinit var binding : DialogLoginRequestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogLoginRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val params = window!!.attributes
        params!!.width = ActionBar.LayoutParams.MATCH_PARENT
        params!!.height = WindowManager.LayoutParams.WRAP_CONTENT
        params.windowAnimations = R.style.AnimationPopupStyle

        window!!.attributes = params
        window!!.setGravity(Gravity.BOTTOM)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.loginDialogKakaoLoginBtn.setOnClickListener {
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (LoginClient.instance.isKakaoTalkLoginAvailable(context)) {
                LoginClient.instance.loginWithKakaoTalk(context) { token, error ->
                    Log.i(TAG, "loginWithKakaoTalk ${token?.accessToken} $error")

                }
            } else {
                LoginClient.instance.loginWithKakaoAccount(context) { token, error ->
                    Log.i(TAG, "loginWithKakaoTalk ${token?.accessToken} $error")

                }
            }
        }
    }

    override fun show() {
        super.show()
    }

}