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
import com.example.squardcoupangeats.src.main.address.adapter.AddressListAdapter
import com.example.squardcoupangeats.src.main.home.models.FranchiseData
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.list_home_frag_franchise_recyclerview.view.*
import kotlinx.android.synthetic.main.list_home_frag_promotion_view_pager.view.*

class HomeFranchiseAdapter(private val franchiseList: ArrayList<FranchiseData>) : RecyclerView.Adapter<HomeFranchiseAdapter.CustomViewholder>(){

    interface FranchiseStoreItemClick {
        fun onClick(view: View, position: Int)
    }
    var franchiseStoreItemClick : HomeFranchiseAdapter.FranchiseStoreItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_home_frag_franchise_recyclerview, parent, false)

        return HomeFranchiseAdapter.CustomViewholder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        Glide.with(holder.view.context).load(franchiseList[position].franchisePhotoUrl).into(holder.storeImg)
        holder.storeName.text = franchiseList[position].franchiseName
        holder.storeDistance.text = franchiseList[position].franchiseDistance

        // storeStar이 null && reviewCount가 0면 => Gone
        if(franchiseList[position].franchiseStar == null || franchiseList[position].franchiseReviewCnt == 0) {
            holder.storeStarAndReviewLO.visibility = View.GONE
        } else {
            holder.storeReview.text = franchiseList[position].franchiseStar.toString() +
                    "(${franchiseList[position].franchiseReviewCnt})" + " ∙ "
        }
        // coupon != null 이면, coupon은 visible, delivery fee는 gone
        // coupon == null 이면, coupon은 gone, delivery fee는 visible
        if(franchiseList[position].franchiseCouponInfo != null) {
            holder.storeCouponLO.visibility = View.VISIBLE
            holder.storeCoupon.text = franchiseList[position].franchiseCouponInfo
            holder.storeDeliveryCost.visibility = View.GONE
        } else {
            holder.storeCouponLO.visibility = View.GONE
            holder.storeDeliveryCost.visibility = View.VISIBLE
            holder.storeDeliveryCost.text = franchiseList[position].franchiseDeliveryFee
        }

        if(franchiseStoreItemClick != null) {
            holder.view.setOnClickListener {
                franchiseStoreItemClick?.onClick(it, position)
            }
        }
    }

    override fun getItemCount(): Int = franchiseList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val storeImg : ImageView = view.list_franchise_item_image_view
        val storeName : TextView = view.list_franchise_item_store_name
        val storeReview : TextView = view.list_franchise_item_star_and_review
        val storeDeliveryCost : TextView = view.list_franchise_item_delivery_cost
        val storeDistance : TextView = view.list_franchise_item_distance
        val storeStarAndReviewLO: LinearLayout = view.list_franchise_item_star_and_review_layout
        val storeCouponLO : LinearLayout = view.list_franchise_item_coupon_layout
        val storeCoupon : TextView = view.list_franchise_item_coupon_tv
    }
}