package com.example.squardcoupangeats.src.main.login

import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass.Companion.OAUTH_CLIENT_ID
import com.example.squardcoupangeats.config.ApplicationClass.Companion.OAUTH_CLIENT_NAME
import com.example.squardcoupangeats.config.ApplicationClass.Companion.OAUTH_CLIENT_SECRET
import com.example.squardcoupangeats.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.squardcoupangeats.config.ApplicationClass.Companion.loginFlag
import com.example.squardcoupangeats.config.ApplicationClass.Companion.sSharedPreferences
import com.example.squardcoupangeats.databinding.DialogLoginRequestBinding
import com.example.squardcoupangeats.src.main.MainActivity
import com.example.squardcoupangeats.src.main.login.models.LoginResponse
import com.example.squardcoupangeats.src.main.login.models.PostLoginRequest
import com.kakao.sdk.auth.LoginClient
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler

class LoginRequestDialog(context: Context, private val activity: Activity) : Dialog(context), LoginRequestDialogView {

    val TAG = "tag"
    private val edit = sSharedPreferences.edit()
    private lateinit var mOAuthLoginModule : OAuthLogin

    private lateinit var binding : DialogLoginRequestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Dialog 기본 세팅
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogLoginRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val params = window!!.attributes
        params!!.width = ActionBar.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        params.windowAnimations = R.style.AnimationPopupStyle

        window!!.attributes = params
        window!!.setGravity(Gravity.BOTTOM)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 카카오 로그인 버튼 클릭
        binding.loginDialogKakaoLoginBtn.setOnClickListener {
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (LoginClient.instance.isKakaoTalkLoginAvailable(context)) {
                LoginClient.instance.loginWithKakaoTalk(context) { token, error ->
                    Log.i(TAG, "loginWithKakaoTalk ${token?.accessToken} $error")
                    // 서버로 AccessToken 보내기
                    LoginService(this).tryPostKakaoLogin(PostLoginRequest(accessToken = token!!.accessToken))
                    // 로그인 유지를 위한 Flag
                    loginFlag = 1
                    // 로그인 Dialog 종료
                    dismiss()

                }
            } else {
                LoginClient.instance.loginWithKakaoAccount(context) { token, error ->
                    Log.i(TAG, "loginWithKakaoTalk ${token?.accessToken} $error")
                    // 서버로 AccessToken 보내기
                    LoginService(this).tryPostKakaoLogin(PostLoginRequest(accessToken = token!!.accessToken))
                    // 로그인 유지를 위한 Flag
                    loginFlag = 1
                    edit.putInt("save login state", loginFlag).apply()
                    // 로그인 Dialog 종료
                    dismiss()
                }
            }
        }

        // 네이버 로그인
        mOAuthLoginModule = OAuthLogin.getInstance()
        mOAuthLoginModule.init(activity.applicationContext, OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET, OAUTH_CLIENT_NAME)
        mOAuthLoginModule.startOauthLoginActivity(activity, mOAuthLoginHandler)
    }

    override fun show() {
        super.show()
    }

    @SuppressLint("CommitPrefEdits")
    override fun onPostLoginSuccess(response: LoginResponse) {
        Log.d(TAG, "성공 : ${response.message}")

        // jwtToken 저장하기
        edit.putString(X_ACCESS_TOKEN, response.result.jwt)
        edit.apply()
    }

    override fun onPostLoginFailure(message: String) {
        Log.d(TAG, "오류 : $message")
    }

    private val mOAuthLoginHandler: OAuthLoginHandler = @SuppressLint("HandlerLeak")
    object : OAuthLoginHandler() {
        override fun run(success: Boolean) {
            if (success) {
                val accessToken: String = mOAuthLoginModule.getAccessToken(activity.applicationContext)
                Log.i(TAG, "loginWithNaver $accessToken")
                // 서버로 Access Token 보내기
                LoginService(this@LoginRequestDialog).tryPostNaverLogin(PostLoginRequest(accessToken = accessToken))
                // 로그인 유지를 위한 Flag
                loginFlag = 1
                edit.putInt("save login state", loginFlag).apply()
                // 로그인 Dialog 종료
                dismiss()

//                val refreshToken: String = mOAuthLoginModule.getRefreshToken(baseContext)
//                val expiresAt: Long = mOAuthLoginModule.getExpiresAt(baseContext)
//                val tokenType: String = mOAuthLoginModule.getTokenType(baseContext)
//                var intent = Intent(this, )
            } else {
                val errorCode: String = mOAuthLoginModule.getLastErrorCode(activity.applicationContext).code
                val errorDesc = mOAuthLoginModule.getLastErrorDesc(activity.applicationContext)

                Toast.makeText(
                    activity.applicationContext, "errorCode:" + errorCode
                            + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

}