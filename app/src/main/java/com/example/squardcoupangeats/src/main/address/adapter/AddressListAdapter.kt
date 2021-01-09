package com.example.squardcoupangeats.src.main.address.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.squardcoupangeats.R
import kotlinx.android.synthetic.main.list_address_list_frag_recyclerview.view.*

data class SearchedAddressData(
        val placeName : String,
        val placeAddress : String
)

class AddressListAdapter(private val addressList : MutableList<SearchedAddressData>) : RecyclerView.Adapter<AddressListAdapter.CustomViewholder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_address_list_frag_recyclerview, parent, false)

        return CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        holder.placeName.text = addressList[position].placeName
        holder.placeAddress.text = addressList[position].placeAddress
    }

    override fun getItemCount(): Int = addressList.size

    class CustomViewholder(val view : View) : RecyclerView.ViewHolder(view) {
        val placeName : TextView = view.list_address_list_item_place_name
        val placeAddress : TextView = view.list_address_list_item_place_address

    }


}