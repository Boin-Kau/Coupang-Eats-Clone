package com.example.squardcoupangeats.src.main.favorite.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.src.main.favorite.models.ResultHartStore
import com.example.squardcoupangeats.src.main.store.models.MenuListData
import kotlinx.android.synthetic.main.list_favorite_activity_recyclerview.view.*
import kotlinx.android.synthetic.main.list_store_activity_menu_category_recyclerview.view.*

class FavoriteStoreAdapter(private val hartStoreList : ArrayList<ResultHartStore>) : RecyclerView.Adapter<FavoriteStoreAdapter.CustomViewholder>()  {

    interface FavoriteStoreItemClick {
        fun onClick(view: View, position: Int)
    }
    var favoriteStoreItemClick : FavoriteStoreAdapter.FavoriteStoreItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_favorite_activity_recyclerview, parent, false)
        return FavoriteStoreAdapter.CustomViewholder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        Glide.with(holder.view.context).load(hartStoreList[position].storePhoto).into(holder.storeImg)
        holder.storeName.text = hartStoreList[position].storeName
        holder.starAndReview.text = hartStoreList[position].storeStar.toString() + "(${hartStoreList[position].reviewCount})"
        holder.infoDetail.text = hartStoreList[position].distance + " * " +
                hartStoreList[position].deliveryTime + " * " +
                hartStoreList[position].deliveryFee

        if(favoriteStoreItemClick != null) {
            holder.view.setOnClickListener {
                favoriteStoreItemClick?.onClick(it, position)
            }
        }
    }

    override fun getItemCount(): Int = hartStoreList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val storeName: TextView = view.list_favorite_activity_item_store_name
        val starAndReview : TextView = view.list_favorite_activity_item_star_and_review
        val infoDetail : TextView = view.list_favorite_activity_item_info_detail
        val storeImg : ImageView = view.list_favorite_activity_item_iv
    }
}
