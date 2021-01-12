package com.example.squardcoupangeats.src.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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

    interface NewStoreItemClick {
        fun onClick(view: View, position: Int)
    }
    var newStoreItemClick : HomeNewStoreAdapter.NewStoreItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_home_frag_franchise_recyclerview, parent, false)

        return HomeNewStoreAdapter.CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {

        Glide.with(holder.view.context).load(newStoreList[position].newStorePhotoUrl).into(holder.storeImg)
        holder.storeName.text = newStoreList[position].newStoreName
        holder.storeDistance.text = newStoreList[position].newStoreDistance

        // storeStar이 null && reviewCount가 0면 => Gone
        if(newStoreList[position].newStoreStar == null || newStoreList[position].newStoreReviewCnt == 0) {
            holder.storeStarAndReviewLO.visibility = View.GONE
        } else {
            holder.storeReview.text = newStoreList[position].newStoreStar.toString() +
                    "(${newStoreList[position].newStoreReviewCnt})" + " ∙ "
        }
        // coupon != null 이면, coupon은 visible, delivery fee는 gone
        // coupon == null 이면, coupon은 gone, delivery fee는 visible
        if(newStoreList[position].newStoreCouponInfo != null) {
            holder.storeCouponLO.visibility = View.VISIBLE
            holder.storeCoupon.text = newStoreList[position].newStoreCouponInfo
            holder.storeDeliveryCost.visibility = View.GONE
        } else {
            holder.storeCouponLO.visibility = View.GONE
            holder.storeDeliveryCost.visibility = View.VISIBLE
            holder.storeDeliveryCost.text = newStoreList[position].newStoreDeliveryFee
        }

        if(newStoreItemClick != null) {
            holder.view.setOnClickListener {
                newStoreItemClick?.onClick(it, position)
            }
        }
    }

    override fun getItemCount(): Int = newStoreList.size

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