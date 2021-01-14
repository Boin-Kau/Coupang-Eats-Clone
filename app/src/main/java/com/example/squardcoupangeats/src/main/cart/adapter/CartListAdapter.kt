package com.example.squardcoupangeats.src.main.cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.src.main.cart.models.ResultCartList
import com.example.squardcoupangeats.src.main.favorite.adapter.FavoriteStoreAdapter
import kotlinx.android.synthetic.main.list_cart_activity_cart_list_recyclerview.view.*

class CartListAdapter(private val cartList: ArrayList<ResultCartList>) : RecyclerView.Adapter<CartListAdapter.CustomViewholder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_cart_activity_cart_list_recyclerview, parent, false)
        return CartListAdapter.CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        holder.menuIndex.text = cartList[position].menuIdx.toString()
        holder.menuName.text = cartList[position].menuName
        holder.price.text = cartList[position].price
        var option = ""
        for(opt in cartList[position].option) {
            option += "$opt "
        }
        holder.menuOption.text = option
    }

    override fun getItemCount(): Int = cartList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val menuIndex : TextView = view.list_cart_activity_cart_list_item_index
        val menuName : TextView = view.list_cart_activity_cart_list_item_name
        val menuOption : TextView = view.list_cart_activity_cart_list_item_option
        val price : TextView = view.list_cart_activity_cart_list_item_price
    }
}