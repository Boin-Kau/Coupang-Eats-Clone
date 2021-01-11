package com.example.squardcoupangeats.src.main.store.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.squardcoupangeats.R
import kotlinx.android.synthetic.main.list_store_activity_top_image_view_pager.view.*

class StoreTopImageAdapter(private val imageList : ArrayList<String>) : PagerAdapter(){

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.list_store_activity_top_image_view_pager, container, false)

        Glide.with(view.context).load(imageList[position]).into(view.list_store_top_image_item_image_view)
        container.addView(view)

        return view
    }

    override fun getCount(): Int = imageList.size

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }
    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View?)
   }
}
