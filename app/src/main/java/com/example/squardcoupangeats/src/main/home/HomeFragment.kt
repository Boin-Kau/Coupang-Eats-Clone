package com.example.squardcoupangeats.src.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragmentHomeBinding
import com.example.squardcoupangeats.src.main.home.adapter.HomeCategoryAdapter
import com.example.squardcoupangeats.src.main.home.adapter.HomePromotionAdapter
import com.example.squardcoupangeats.src.main.home.models.CategoryItem

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val promotionAdapter = HomePromotionAdapter()
        binding.homeFragPromotionViewPager.adapter = promotionAdapter

        val categoryList = arrayListOf(
            CategoryItem("https://instagram.fvno2-1.fna.fbcdn.net/v/t51.2885-15/sh0.08/e35/s640x640/131528464_865361007672277_8546311804083962608_n.jpg?_nc_ht=instagram.fvno2-1.fna.fbcdn.net&_nc_cat=101&_nc_ohc=OB17iFDDP4EAX-VaKfE&tp=1&oh=41b217745f695da1c8b526f72e168fd3&oe=601C0A73", "1인분"),
            CategoryItem("","한식"),
            CategoryItem("https://rereco.co/wp-content/uploads/2018/02/best_menu09.jpg","치킨"),
            CategoryItem("", "분식"),
            CategoryItem("", "돈까스"),
            CategoryItem("", "족발/보쌈"),
            CategoryItem("", "찜/탕"),
            CategoryItem("", "구이"),
            CategoryItem("", "피자"),
            CategoryItem("", "중식"),
            CategoryItem("", "일식"),
            CategoryItem("", "회/해물"),
            CategoryItem("", "양식"),
            CategoryItem("", "커피/차"),
            CategoryItem("", "디저트"),
            CategoryItem("", "간식"),
            CategoryItem("", "아시안"),
            CategoryItem("", "샌드위치"),
            CategoryItem("", "샐러드"),
            CategoryItem("", "버거"),
            CategoryItem("", "멕시칸"),
            CategoryItem("", "도시락"),
            CategoryItem("", "죽"),
            CategoryItem("", "프랜차이즈")
        )
        val categoryAdapter = HomeCategoryAdapter(categoryList)
        binding.homeFragCategoryRecyclerview.adapter = categoryAdapter
        binding.homeFragCategoryRecyclerview.layoutManager = LinearLayoutManager(activity).also { it.orientation = LinearLayoutManager.HORIZONTAL}
    }
}