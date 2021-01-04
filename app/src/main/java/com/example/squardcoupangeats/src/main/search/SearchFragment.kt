package com.example.squardcoupangeats.src.main.search


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragmentSearchBinding
import com.example.squardcoupangeats.src.main.home.HomeFragmentView
import com.example.squardcoupangeats.src.main.home.adapter.HomeCategoryAdapter
import com.example.squardcoupangeats.src.main.home.models.StoreResponse

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = SearchCategoryAdapter(ApplicationClass.categoryNameList, ApplicationClass.categoryImgUrlList)
        binding.searchFragCategoryRecyclerview.adapter = categoryAdapter
        binding.searchFragCategoryRecyclerview.layoutManager = GridLayoutManager(activity, 2)
    }







}