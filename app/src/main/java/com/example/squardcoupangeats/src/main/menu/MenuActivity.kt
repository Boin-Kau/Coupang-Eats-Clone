package com.example.squardcoupangeats.src.main.menu

import android.os.Bundle
import android.util.Log
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseActivity
import com.example.squardcoupangeats.databinding.ActivityMenuBinding
import com.example.squardcoupangeats.src.main.menu.models.SpecificMenuResponse
import com.example.squardcoupangeats.src.main.menu.service.MenuActivityView

class MenuActivity : BaseActivity<ActivityMenuBinding>(ActivityMenuBinding::inflate), MenuActivityView {

    val TAG = "tag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Store Activity Recyclerview에 클릭 이벤트가 발생하면
        // 해당 position의 index를 intent에 담아 Menu Activity로 startActivity
        // Menu Activity에서는 hasExtra로 menuIndex를 키값으로 가지는 value를 꺼내, MenuService의 메소드로 index값을 파라미터로 넘겨줌

        setSupportActionBar(findViewById(R.id.toolbar))
        findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onGetSpecificMenuSuccess(response: SpecificMenuResponse) {
        Log.d(TAG, response.message)
    }

    override fun onGetSpecificMenuFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("오류", message)
    }
}