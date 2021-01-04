package com.example.squardcoupangeats.src.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.src.main.home.models.FranchiseData
import com.example.squardcoupangeats.src.main.home.models.NewStoreData
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.list_home_frag_franchise_recyclerview.view.*
import kotlinx.android.synthetic.main.list_home_frag_new_store_recyclerview.view.*

class HomeNewStoreAdapter(private val newStoreList: ArrayList<NewStoreData>) : RecyclerView.Adapter<HomeNewStoreAdapter.CustomViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_home_frag_new_store_recyclerview, parent, false)

        return HomeNewStoreAdapter.CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {

        Glide.with(holder.view.context).load(newStoreList[position].newStorePhotoUrl).into(holder.storeImg)
        holder.storeName.text = newStoreList[position].newStoreName
        holder.storeInfo.text = newStoreList[position].newStoreDistance
        holder.storeDeliveryCost.text = newStoreList[position].newStoreDeliveryFee
    }

    override fun getItemCount(): Int = newStoreList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val storeImg : ImageView = view.list_new_store_item_image_view
        val storeName : TextView = view.list_new_store_item_store_name
        val storeInfo : TextView = view.list_new_store_item_info_detail
        val storeDeliveryCost : TextView = view.list_new_store_item_delivery_cost

    }
}