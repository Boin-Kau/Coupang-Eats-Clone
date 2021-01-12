package com.example.squardcoupangeats.src.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.ApplicationClass.Companion.loginFlag
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragmentHomeBinding
import com.example.squardcoupangeats.src.main.MainActivity
import com.example.squardcoupangeats.src.main.address.AddressActivity
import com.example.squardcoupangeats.src.main.home.adapter.*
import com.example.squardcoupangeats.src.main.home.models.ResultStore
import com.example.squardcoupangeats.src.main.home.models.StoreResponse
import com.example.squardcoupangeats.src.main.login.LoginRequestDialog
import com.example.squardcoupangeats.src.main.search.SearchFragment
import com.example.squardcoupangeats.src.main.store.StoreActivity
import java.util.*

@Suppress("DEPRECATION")
class HomeFragment(val placeName : String) : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), HomeFragmentView {

    private lateinit var timer : Timer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        HomeService(this).tryGetStores()

        binding.homeFragAddressTv.text = placeName

        binding.homeFragChangeToAddressActivity.setOnClickListener {
            when(loginFlag) {
                1 -> activity!!.startActivity(Intent(activity, AddressActivity::class.java))
                0 -> LoginRequestDialog(activity!!, activity!!).show()
            }
        }
    }

    override fun onGetStoreSuccess(response: StoreResponse) {

        val result = response.result
        setAdapter(result)

        for(i in result.sortedStore) {
            Log.d("HomeFragment", i.toString())
        }
    }

    override fun onGetStoreFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("오류", message)
    }

    private fun setAdapter(result: ResultStore) {
        val promotionList = result.promotion
        val categoryList = result.category
        val franchiseList = result.franchise
        val newStoreList = result.openStore
        val sortedStoreList = result.sortedStore

        for(i in categoryList) {
            ApplicationClass.categoryNameList.add(i.categoryName)
        }

        // 프로모션 구현 : 뷰페이저
        val promotionAdapter = HomePromotionAdapter(promotionList)
        binding.homeFragPromotionViewPager.apply {
            adapter = promotionAdapter

            var currentPage = 0
            val handler = Handler()
            val update = Runnable {
                if(currentPage == 3) {
                    currentPage = 0
                }
                setCurrentItem(currentPage++, true)
            }
            timer = Timer()
            timer.schedule(object : TimerTask(){
                override fun run() {
                    handler.post(update)
                }

            }, 500, 3000)
        }



        // 카테고리 구현 : 가로 리사이클러뷰
        val categoryAdapter = HomeCategoryAdapter(categoryList)
        binding.homeFragCategoryRecyclerview.adapter = categoryAdapter
        binding.homeFragCategoryRecyclerview.layoutManager = LinearLayoutManager(activity).also { it.orientation = LinearLayoutManager.HORIZONTAL}

        // 프랜차이즈 구현 : 가로 리사이클러뷰
        val franchiseAdapter = HomeFranchiseAdapter(franchiseList)
        binding.homeFragFranchiseRecyclerview.adapter = franchiseAdapter
        binding.homeFragFranchiseRecyclerview.layoutManager = LinearLayoutManager(activity).also { it.orientation = LinearLayoutManager.HORIZONTAL}
        franchiseAdapter.franchiseStoreItemClick = object : HomeFranchiseAdapter.FranchiseStoreItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(activity, StoreActivity::class.java)
                intent.putExtra("storeIndex", franchiseList[position].franchiseIndex)
                activity!!.startActivity(intent)
            }
        }

        // Sorted Store 구현 : 세로 리사이클러뷰
        val sortedAdapter = HomeSortedAdapter(sortedStoreList)
        binding.homeFragSortedStoreRecyclerview.adapter = sortedAdapter
        sortedAdapter.sortedStoreItemClick = object : HomeSortedAdapter.SortedStoreItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(activity, StoreActivity::class.java)
                intent.putExtra("storeIndex", sortedStoreList[position].sortedStoreIndex)
                activity!!.startActivity(intent)
            }

        }

        // New Store 구현 : 가로 리사이클러뷰
        val newStoreAdapter = HomeNewStoreAdapter(newStoreList)
        binding.homeFragNewStoreRecyclerview.adapter = newStoreAdapter
        binding.homeFragNewStoreRecyclerview.layoutManager = LinearLayoutManager(activity).also { it.orientation = LinearLayoutManager.HORIZONTAL}
        newStoreAdapter.newStoreItemClick = object : HomeNewStoreAdapter.NewStoreItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(activity, StoreActivity::class.java)
                intent.putExtra("storeIndex", newStoreList[position].newStoreIndex)
                activity!!.startActivity(intent)
            }

        }
    }
}