package com.example.squardcoupangeats.src.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.src.main.home.models.FranchiseAndNewStoreItem
import kotlinx.android.synthetic.main.list_home_frag_franchise_recyclerview.view.*

class HomeFranchiseAdapter(private val franchiseList: ArrayList<FranchiseAndNewStoreItem>) : RecyclerView.Adapter<HomeFranchiseAdapter.CustomViewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_home_frag_franchise_recyclerview, parent, false)

        return HomeFranchiseAdapter.CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {

    }

    override fun getItemCount(): Int = franchiseList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val storeName : TextView = view.list_franchise_item_store_name
        val storeInfo : TextView = view.list_franchise_item_info_detail
        val storeDeliveryCost : TextView = view.list_franchise_item_delivery_cost
    }

}