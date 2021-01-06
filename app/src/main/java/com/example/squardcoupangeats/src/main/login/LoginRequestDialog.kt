package com.example.squardcoupangeats.src.main.login

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.example.squardcoupangeats.R

class LoginRequestDialog(context: Context) {

    private val dialog = Dialog(context)

    fun showDialog() {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.login_request_dialog)

        val params = dialog.window!!.attributes
        params!!.width = ActionBar.LayoutParams.MATCH_PARENT
        params!!.height = WindowManager.LayoutParams.WRAP_CONTENT
        params.windowAnimations = R.style.AnimationPopupStyle
        dialog.window!!.attributes = params
        val window = dialog.window
        window!!.setGravity(Gravity.BOTTOM)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()
    }
}