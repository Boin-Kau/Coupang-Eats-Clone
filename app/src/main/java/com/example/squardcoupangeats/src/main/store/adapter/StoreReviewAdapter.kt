package com.example.squardcoupangeats.src.main.store.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.src.main.home.models.CategoryData
import com.example.squardcoupangeats.src.main.store.models.ResultPhotoReview
import kotlinx.android.synthetic.main.list_home_frag_category_recyclerview.view.*
import kotlinx.android.synthetic.main.list_store_activity_review_recyclerview.view.*

class StoreReviewAdapter(private val photoReviewList : ArrayList<ResultPhotoReview>) : RecyclerView.Adapter<StoreReviewAdapter.CustomViewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_store_activity_review_recyclerview, parent, false)
        return StoreReviewAdapter.CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        Glide.with(holder.view.context).load(photoReviewList[position].reviewPhoto).into(holder.reviewPhoto)
        holder.reviewContent.text = photoReviewList[position].content
        val reviewStar = photoReviewList[position].reviewStar
        when(reviewStar) {
            1 -> holder.reviewRatingBar.rating = 1F
            2 -> holder.reviewRatingBar.rating = 2F
            3 -> holder.reviewRatingBar.rating = 3F
            4 -> holder.reviewRatingBar.rating = 4F
            5 -> holder.reviewRatingBar.rating = 5F
            else -> holder.reviewRatingBar.rating = reviewStar.toFloat()
        }
    }

    override fun getItemCount(): Int = photoReviewList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val reviewPhoto: ImageView = view.list_review_item_iv
        val reviewRatingBar: AppCompatRatingBar = view.list_review_item_rating_bar
        val reviewContent: TextView = view.list_review_item_content_tv
    }
}
