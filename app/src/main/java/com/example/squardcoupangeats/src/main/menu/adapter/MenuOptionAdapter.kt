package com.example.squardcoupangeats.src.main.menu.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.ApplicationClass.Companion.radioBtnSelected1
import com.example.squardcoupangeats.src.main.menu.models.OptMenuListData
import kotlinx.android.synthetic.main.list_menu_activity_option_menu_recyclerview.view.*

class MenuOptionAdapter(private val optionMenuList: ArrayList<OptMenuListData>) : RecyclerView.Adapter<MenuOptionAdapter.CustomViewholder>() {

    interface MenuOptionItemClick {
        fun onClick(view: View, position: Int)
    }
    var menuOptionItemClick : MenuOptionAdapter.MenuOptionItemClick? = null
    private var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_menu_activity_option_menu_recyclerview, parent, false)

        return MenuOptionAdapter.CustomViewholder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {

        if(menuOptionItemClick != null) {
            holder.view.setOnClickListener {
                menuOptionItemClick?.onClick(it, position)
            }
        }

        holder.radioBtn.tag = optionMenuList[position].menuOptIdx
        holder.menuName.text = optionMenuList[position].menuOptName
        if(optionMenuList[position].menuOptPrice != null) {
            holder.extraFee.text = optionMenuList[position].menuOptPrice
        } else {
            holder.extraFee.visibility = View.GONE
        }

        holder.view.setOnClickListener {
            radioBtnSelected1 = position
            notifyDataSetChanged()
        }

        holder.radioBtn.setOnClickListener {
            radioBtnSelected1 = position
            notifyDataSetChanged()
        }
        holder.radioBtn.isChecked = (radioBtnSelected1 == position)
    }

    override fun getItemCount(): Int = optionMenuList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val radioBtn : RadioButton = view.list_menu_activity_option_menu_check_btn
        val menuName : TextView = view.list_menu_activity_option_menu_name
        val extraFee : TextView = view.list_menu_activity_option_menu_extra_fee
    }
}