package com.example.squardcoupangeats.src.main.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.src.main.home.models.SortedStoreData
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.list_home_frag_sorted_recyclerview.view.*

class HomeSortedAdapter(private val sortedStoreList: ArrayList<SortedStoreData>) : RecyclerView.Adapter<HomeSortedAdapter.CustomViewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_home_frag_sorted_recyclerview, parent, false)

        return HomeSortedAdapter.CustomViewholder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {

        Glide.with(holder.view.context).load(sortedStoreList[position].sortedStoreImgList[0]).into(holder.storeImage)
        holder.storeName.text = sortedStoreList[position].sortedStoreName
        holder.storeInfo.text = sortedStoreList[position].sortedStoreStar.toString() +
                "(${sortedStoreList[position].sortedStoreReviewCnt})" +
                " * ${sortedStoreList[position].sortedStoreDistance}"
        holder.deliveryTime.text = sortedStoreList[position].sortedStoreDeliveryTime
    }

    override fun getItemCount() = sortedStoreList.size

    class CustomViewholder(val view : View) : RecyclerView.ViewHolder(view) {
        val storeImage : ImageView = view.list_sorted_item_image_view
        val storeName : TextView = view.list_sorted_item_store_name
        val storeInfo : TextView = view.list_sorted_item_info_detail
        val deliveryTime : TextView = view.list_sorted_item_delivery_time
    }
}