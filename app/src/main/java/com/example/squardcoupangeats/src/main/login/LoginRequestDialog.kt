package com.example.squardcoupangeats.src.main.login

import android.annotation.SuppressLint
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
import com.example.squardcoupangeats.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.squardcoupangeats.config.ApplicationClass.Companion.loginFlag
import com.example.squardcoupangeats.config.ApplicationClass.Companion.sSharedPreferences
import com.example.squardcoupangeats.databinding.DialogLoginRequestBinding
import com.example.squardcoupangeats.src.main.login.models.KakaoLoginResponse
import com.example.squardcoupangeats.src.main.login.models.PostKakaoLoginRequest
import com.kakao.sdk.auth.LoginClient

class LoginRequestDialog(context: Context) : Dialog(context), LoginRequestDialogView {

    val TAG = "tag"
    private val edit = sSharedPreferences.edit()

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
                    // 서버로 AccessToken 보내기
                    LoginService(this).tryPostKakaoLogin(PostKakaoLoginRequest(kakaoAccessToken = token!!.accessToken))
                    // 로그인 유지를 위한 Flag
                    loginFlag = 1
                    // 로그인 Dialog 종료
                    dismiss()

                }
            } else {
                LoginClient.instance.loginWithKakaoAccount(context) { token, error ->
                    Log.i(TAG, "loginWithKakaoTalk ${token?.accessToken} $error")
                    // 서버로 AccessToken 보내기
                    LoginService(this).tryPostKakaoLogin(PostKakaoLoginRequest(kakaoAccessToken = token!!.accessToken))
                    // 로그인 유지를 위한 Flag
                    loginFlag = 1
                    edit.putInt("save login state", loginFlag).apply()
                    // 로그인 Dialog 종료
                    dismiss()
                }
            }
        }
    }

    override fun show() {
        super.show()
    }

    @SuppressLint("CommitPrefEdits")
    override fun onPostLoginSuccess(response: KakaoLoginResponse) {
        Log.d(TAG, "성공 : ${response.message}")

        // jwtToken 저장하기

        edit.putString(X_ACCESS_TOKEN, response.result.jwt)
        edit.apply()
    }

    override fun onPostLoginFailure(message: String) {
        Log.d(TAG, "오류 : $message")
    }

}