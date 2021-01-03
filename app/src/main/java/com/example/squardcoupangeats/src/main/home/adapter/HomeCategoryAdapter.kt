package com.example.squardcoupangeats.src.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.squardcoupangeats.R
import kotlinx.android.synthetic.main.list_home_frag_category_recyclerview.view.*

class HomeCategoryAdapter : RecyclerView.Adapter<HomeCategoryAdapter.CustomViewholder>() {

    // 임시 리스트
    val itemList = listOf("1인분", "2", "3", "4", "5", "6", "7")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_home_frag_category_recyclerview, parent, false)

        return CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        holder.view.list_category_item_text_view.text = itemList[position]
    }

    override fun getItemCount(): Int = itemList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {

    }


}