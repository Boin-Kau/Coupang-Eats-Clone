package com.example.squardcoupangeats.src.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.squardcoupangeats.R
import kotlinx.android.synthetic.main.list_home_frag_promotion_view_pager.view.*

class HomePromotionAdapter : PagerAdapter() {

    // 임시 리스트
    val itemList = listOf("page1", "page2", "page3", "page4", "page5", "page6")

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.list_home_frag_promotion_view_pager, container, false)

        // glide로 사진 url 가져와서 image에 뿌릴 예정
        // 임시 : 페이지 개수 세기 - promotion 6개
        view.test_viewpager_textview.text = itemList[position]

        container.addView(view)
        return view
    }

    override fun getCount(): Int = itemList.size

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }
}