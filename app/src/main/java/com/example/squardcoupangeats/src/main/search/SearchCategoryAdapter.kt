package com.example.squardcoupangeats.src.main.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.ApplicationClass.Companion.categoryNameList
import kotlinx.android.synthetic.main.list_search_frag_category_recyclerview.view.*

class SearchCategoryAdapter() : RecyclerView.Adapter<SearchCategoryAdapter.CustomViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_search_frag_category_recyclerview, parent, false)

        return SearchCategoryAdapter.CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        Glide.with(holder.view.context).load(ApplicationClass.categoryImageList[position]).override(170,110).into(holder.categoryImg)
        holder.categoryName.text = categoryNameList[position]
    }

    override fun getItemCount(): Int = categoryNameList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val categoryImg : ImageView = view.list_search_frag_category_item_image_view
        val categoryName : TextView = view.list_search_frag_category_item_text_view
    }
}