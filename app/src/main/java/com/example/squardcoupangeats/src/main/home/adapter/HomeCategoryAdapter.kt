package com.example.squardcoupangeats.src.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.src.main.home.models.CategoryData
import kotlinx.android.synthetic.main.list_home_frag_category_recyclerview.view.*


class HomeCategoryAdapter(private val categoryNameList: ArrayList<CategoryData>) : RecyclerView.Adapter<HomeCategoryAdapter.CustomViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryAdapter.CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_home_frag_category_recyclerview, parent, false)

        return HomeCategoryAdapter.CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: HomeCategoryAdapter.CustomViewholder, position: Int) {
        Glide.with(holder.view.context).load(ApplicationClass.categoryImageList[position]).circleCrop().into(holder.categoryImage)
        holder.categoryName.text = categoryNameList[position].categoryName
    }

    override fun getItemCount(): Int = categoryNameList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val categoryImage: ImageView = view.list_category_item_image_view
        val categoryName: TextView = view.list_category_item_text_view
    }
}