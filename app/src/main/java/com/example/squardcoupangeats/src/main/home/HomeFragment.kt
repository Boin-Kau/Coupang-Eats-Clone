package com.example.squardcoupangeats.src.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragmentHomeBinding
import com.example.squardcoupangeats.src.main.home.adapter.HomeCategoryAdapter
import com.example.squardcoupangeats.src.main.home.adapter.HomePromotionAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomePromotionAdapter()
        binding.homeFragPromotionViewPager.adapter = adapter

        val adapter2 = HomeCategoryAdapter()
        binding.homeFragCategoryRecyclerview.adapter = adapter2
        binding.homeFragCategoryRecyclerview.layoutManager = LinearLayoutManager(activity).also { it.orientation = LinearLayoutManager.HORIZONTAL}
    }
}