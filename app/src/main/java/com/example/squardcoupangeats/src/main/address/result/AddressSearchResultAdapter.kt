package com.example.squardcoupangeats.src.main.address.result

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.src.main.address.models.ResultAddress
import kotlinx.android.synthetic.main.list_address_search_result_frag_recyclerview.view.*

class AddressSearchResultAdapter(private val addressList : ArrayList<ResultAddress>) : RecyclerView.Adapter<AddressSearchResultAdapter.CustomViewholder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_address_search_result_frag_recyclerview, parent, false)

        return AddressSearchResultAdapter.CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        holder.addressName.text = addressList[position].place_name
        val roadAddressName = addressList[position].road_address_name
        if(roadAddressName == "") {
            holder.addressType.text = "지번"
            holder.addressDetail.text = addressList[position].address_name
        } else {
            holder.addressType.text = "도로명"
            holder.addressDetail.text = addressList[position].road_address_name
        }

    }

    override fun getItemCount(): Int = addressList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val addressName : TextView = view.list_address_search_result_item_address_name
        val addressType : TextView = view.list_address_search_result_item_type
        val addressDetail : TextView = view.list_address_search_result_item_address_detail
    }
}