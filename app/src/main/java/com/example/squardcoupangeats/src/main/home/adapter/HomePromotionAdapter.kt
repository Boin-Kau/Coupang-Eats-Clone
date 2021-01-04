package com.example.squardcoupangeats.src.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.src.main.home.models.CategoryData
import com.example.squardcoupangeats.src.main.home.models.PromotionData
import kotlinx.android.synthetic.main.list_home_frag_promotion_view_pager.view.*

class HomePromotionAdapter(private val promotionList: ArrayList<PromotionData>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.list_home_frag_promotion_view_pager, container, false)


        Glide.with(view.context).load(promotionList[position].promotionUrl).into(view.list_promotion_item_image_view)

        container.addView(view)
        return view
    }

    override fun getCount(): Int = promotionList.size

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
    }
}