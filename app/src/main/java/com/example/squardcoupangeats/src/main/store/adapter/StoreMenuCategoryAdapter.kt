package com.example.squardcoupangeats.src.main.store.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.src.main.home.adapter.HomeSortedAdapter
import com.example.squardcoupangeats.src.main.store.models.MenuListData
import kotlinx.android.synthetic.main.list_store_activity_menu_category_recyclerview.view.*

class StoreMenuCategoryAdapter(private val menuList : ArrayList<MenuListData>) : RecyclerView.Adapter<StoreMenuCategoryAdapter.CustomViewholder>() {

    interface MenuCategoryItemClick {
        fun onClick(view: View, position: Int)
    }
    var menuCategoryItemClick : StoreMenuCategoryAdapter.MenuCategoryItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_store_activity_menu_category_recyclerview, parent, false)
        return StoreMenuCategoryAdapter.CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        if(menuList[position].menuDetail != null) {
            holder.menuDetail.text = menuList[position].menuDetail
        } else {
            holder.menuDetail.visibility = View.GONE
        }

        if(menuList[position].menuThumbnail != null) {
            Glide.with(holder.view.context).load(menuList[position].menuThumbnail).into(holder.menuThumbnail)
        } else {
            holder.menuThumbnail.visibility = View.GONE
        }
        holder.menuName.text = menuList[position].menuName
        holder.menuPrice.text = menuList[position].menuPrice

        if(menuCategoryItemClick != null) {
            holder.view.setOnClickListener {
                menuCategoryItemClick?.onClick(it, position)
            }
        }
    }

    override fun getItemCount(): Int = menuList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val menuName : TextView = view.list_store_activity_menu_category_menu_name
        val menuPrice : TextView = view.list_store_activity_menu_category_menu_price
        val menuDetail : TextView = view.list_store_activity_menu_category_menu_detail
        val menuThumbnail : ImageView = view.list_store_activity_menu_category_menu_thumbnail
    }
}