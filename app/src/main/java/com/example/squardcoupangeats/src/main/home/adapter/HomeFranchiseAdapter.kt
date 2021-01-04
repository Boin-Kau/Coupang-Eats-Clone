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
import com.example.squardcoupangeats.src.main.home.models.FranchiseData
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.list_home_frag_franchise_recyclerview.view.*
import kotlinx.android.synthetic.main.list_home_frag_promotion_view_pager.view.*

class HomeFranchiseAdapter(private val franchiseList: ArrayList<FranchiseData>) : RecyclerView.Adapter<HomeFranchiseAdapter.CustomViewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_home_frag_franchise_recyclerview, parent, false)

        return HomeFranchiseAdapter.CustomViewholder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {

        Glide.with(holder.view.context).load(franchiseList[position].franchisePhotoUrl).into(holder.storeImg)
        holder.storeName.text = franchiseList[position].franchiseName
        holder.storeInfo.text = franchiseList[position].franchiseStar.toString() +
                "(${franchiseList[position].franchiseReviewCnt})" +
                " * ${franchiseList[position].franchiseDistance}"
        holder.storeDeliveryCost.text = franchiseList[position].franchiseDeliveryFee
    }

    override fun getItemCount(): Int = franchiseList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val storeImg : ImageView = view.list_franchise_item_image_view
        val storeName : TextView = view.list_franchise_item_store_name
        val storeInfo : TextView = view.list_franchise_item_info_detail
        val storeDeliveryCost : TextView = view.list_franchise_item_delivery_cost
    }

}