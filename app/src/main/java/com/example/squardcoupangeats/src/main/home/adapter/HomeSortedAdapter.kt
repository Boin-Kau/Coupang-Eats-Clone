package com.example.squardcoupangeats.src.main.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.src.main.home.models.SortedStoreData
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.list_home_frag_franchise_recyclerview.view.*
import kotlinx.android.synthetic.main.list_home_frag_sorted_recyclerview.view.*

class HomeSortedAdapter(private val sortedStoreList: ArrayList<SortedStoreData>) : RecyclerView.Adapter<HomeSortedAdapter.CustomViewholder>() {

    interface SortedStoreItemClick {
        fun onClick(view: View, position: Int)
    }
    var sortedStoreItemClick : HomeSortedAdapter.SortedStoreItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_home_frag_sorted_recyclerview, parent, false)

        return HomeSortedAdapter.CustomViewholder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        Glide.with(holder.view.context).load(sortedStoreList[position].sortedStoreImgList[0]).into(holder.storeImage1)
        Glide.with(holder.view.context).load(sortedStoreList[position].sortedStoreImgList[1]).into(holder.storeImage2)
        Glide.with(holder.view.context).load(sortedStoreList[position].sortedStoreImgList[2]).into(holder.storeImage3)
        holder.storeName.text = sortedStoreList[position].sortedStoreName
        holder.storeDeliveryTime.text = sortedStoreList[position].sortedStoreDeliveryTime
        holder.storeDistance.text = sortedStoreList[position].sortedStoreDistance + " ∙ "
        holder.storeDeliveryCost.text = sortedStoreList[position].sortedStoreDeliveryFee

        if(sortedStoreList[position].isCheetah == "N") {
            holder.isCheetah.visibility = View.INVISIBLE
        }

        // storeStar이 null && reviewCount가 0면 => Gone
        if(sortedStoreList[position].sortedStoreStar == null || sortedStoreList[position].sortedStoreReviewCnt == 0) {
            holder.storeStarAndReviewLO.visibility = View.GONE
        } else {
            holder.storeReview.text = sortedStoreList[position].sortedStoreStar.toString() +
                    "(${sortedStoreList[position].sortedStoreReviewCnt})" + " ∙ "
        }
        // coupon == null이면, CouponLO => Gone
        if(sortedStoreList[position].sortedStoreCouponInfo != null) {
            holder.storeCoupon.text = sortedStoreList[position].sortedStoreCouponInfo
        } else {
            holder.storeCouponLO.visibility = View.GONE
        }

        if(sortedStoreItemClick != null) {
            holder.view.setOnClickListener {
                sortedStoreItemClick?.onClick(it, position)
            }
        }
    }

    override fun getItemCount() = sortedStoreList.size

    class CustomViewholder(val view : View) : RecyclerView.ViewHolder(view) {
        val storeImage1 : ImageView = view.list_sorted_item_image_view1
        val storeImage2 : ImageView = view.list_sorted_item_image_view2
        val storeImage3 : ImageView = view.list_sorted_item_image_view3
        val storeName : TextView = view.list_sorted_item_store_name
        val storeReview : TextView = view.list_sorted_item_star_and_review
        val storeStarAndReviewLO: LinearLayout = view.list_sorted_item_star_and_review_layout
        val storeDistance : TextView = view.list_sorted_item_distance
        val storeDeliveryCost : TextView = view.list_sorted_item_delivery_cost
        val storeCouponLO : LinearLayout = view.list_sorted_item_coupon_layout
        val storeCoupon : TextView = view.list_sorted_item_coupon_tv
        val isCheetah : ImageView = view.list_sorted_item_is_cheetah_iv
        val storeDeliveryTime : TextView = view.list_sorted_item_delivery_time
    }
}