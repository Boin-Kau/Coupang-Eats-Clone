package com.example.squardcoupangeats.src.main.menu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.src.main.menu.models.OptMenuListData
import kotlinx.android.synthetic.main.list_menu_activity_option_checkbox_menu_recyclerview.view.*
import kotlinx.android.synthetic.main.list_menu_activity_option_menu_recyclerview.view.*

class MenuOptionCheckboxAdapter(private val optionMenuList: ArrayList<OptMenuListData>, private var checkboxList : ArrayList<CheckBoxState>) : RecyclerView.Adapter<MenuOptionCheckboxAdapter.CustomViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewholder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_menu_activity_option_checkbox_menu_recyclerview, parent, false)

        return MenuOptionCheckboxAdapter.CustomViewholder(view)
    }

    override fun onBindViewHolder(holder: CustomViewholder, position: Int) {
        holder.menuName.text = optionMenuList[position].menuOptName
        if(optionMenuList[position].menuOptPrice != null) {
            holder.extraFee.text = optionMenuList[position].menuOptPrice
        } else {
            holder.extraFee.visibility = View.GONE
        }
        holder.checkbox.isChecked = checkboxList[position].checked
        holder.checkbox.setOnClickListener {
            val newState : Boolean = !checkboxList[position].isChecked()
            checkboxList[position].checkState = newState
        }
        holder.view.setOnClickListener {
            val newState : Boolean = !checkboxList[position].isChecked()
            checkboxList[position].checkState = newState
        }
        holder.checkbox.isChecked = isChecked(position)
    }

    override fun getItemCount(): Int = optionMenuList.size

    class CustomViewholder(val view: View) : RecyclerView.ViewHolder(view) {
        val checkbox : CheckBox = view.list_menu_activity_option_checkbox_menu_check_btn
        val menuName : TextView = view.list_menu_activity_option_checkbox_menu_name
        val extraFee : TextView = view.list_menu_activity_option_checkbox_menu_extra_fee
    }

    fun isChecked(position: Int) : Boolean = checkboxList[position].checkState

}