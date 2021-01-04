package com.example.squardcoupangeats.src.main.home

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.ApplicationClass
import com.example.squardcoupangeats.config.ApplicationClass.Companion.categoryImgUrlList
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragmentHomeBinding
import com.example.squardcoupangeats.src.main.home.adapter.*
import com.example.squardcoupangeats.src.main.home.models.ResultStore
import com.example.squardcoupangeats.src.main.home.models.StoreResponse

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), HomeFragmentView {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        HomeService(this).tryGetStores()
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

        // 임시
        for(i in 0..10) {
            ApplicationClass.categoryNameList.add(categoryList[i].categoryName)
        }


        // 프로모션 구현 : 뷰페이저
        val promotionAdapter = HomePromotionAdapter(promotionList)
        binding.homeFragPromotionViewPager.adapter = promotionAdapter

        // 카테고리 구현 : 가로 리사이클러뷰
        val categoryAdapter = HomeCategoryAdapter(categoryList, categoryImgUrlList)
        binding.homeFragCategoryRecyclerview.adapter = categoryAdapter
        binding.homeFragCategoryRecyclerview.layoutManager = LinearLayoutManager(activity).also { it.orientation = LinearLayoutManager.HORIZONTAL}

        // 프랜차이즈 구현 : 가로 리사이클러뷰
        val franchiseAdapter = HomeFranchiseAdapter(franchiseList)
        binding.homeFragFranchiseRecyclerview.adapter = franchiseAdapter
        binding.homeFragFranchiseRecyclerview.layoutManager = LinearLayoutManager(activity).also { it.orientation = LinearLayoutManager.HORIZONTAL}

        // Sorted Store 구현 : 세로 리사이클러뷰
        val sortedAdapter = HomeSortedAdapter(sortedStoreList)
        binding.homeFragSortedStoreRecyclerview.adapter = sortedAdapter

        // New Store 구현 : 가로 리사이클러뷰
        val newStoreAdapter = HomeNewStoreAdapter(newStoreList)
        binding.homeFragNewStoreRecyclerview.adapter = newStoreAdapter
        binding.homeFragNewStoreRecyclerview.layoutManager = LinearLayoutManager(activity).also { it.orientation = LinearLayoutManager.HORIZONTAL}
    }

    init {
        categoryImgUrlList.add("https://canary.contestimg.wish.com/api/webimage/58b791aefdcf3e2e8d97cd03-normal.jpg?cache_buster=c9c2f913e3cf0fae4ff4179a31d8a584")
        categoryImgUrlList.add("https://kfood.hyangyeon.sg/wp-content/uploads/2020/04/%EA%B9%80%EC%B9%98%EC%B0%8C%EA%B0%9C2.jpg")
        categoryImgUrlList.add("https://crcf.cookatmarket.com/product/images/2019/10/sewu_1570434837_4368.jpg")
        categoryImgUrlList.add("https://post-phinf.pstatic.net/MjAxOTEwMDFfNjkg/MDAxNTY5OTE5NzUxNDc2.mnGT1DcIaEY9os4ftETl5Bc_SudAwsUq8O3KaqlpQtQg.qhcMdUjcKqBoTC6hR1j7OnsY4BIpK1aulSmv0mlwO14g.JPEG/%EB%B6%84%EC%8B%9D.jpg?type=w1200")
        categoryImgUrlList.add("https://image.auction.co.kr/itemimage/1a/88/06/1a88062d86.jpg")
        categoryImgUrlList.add("https://img.etoday.co.kr/pto_db/2020/03/600/20200316164044_1436800_500_327.JPG")
        categoryImgUrlList.add("https://chosunhwarojib.com/upload/menu_01/2019_03_11/admin_7xRA8_2019_03_11_12_13_36.jpg")
        categoryImgUrlList.add("https://lh3.googleusercontent.com/proxy/0wqCjEtjlCiaxDE9ih2IxPwlWrgDZjijVR2B5pcY2u9AcfJmWh_sZ-mldvET1YaK2Bc9zYbzDrlLizDvp22xfBiDBH3Jsv3iSh4wd9ScOVo37vPTDJ_4wVKNYOCfoyUdjseOuNnY6i3Wu-qX0j1uOVjWXwGHrqKV5lTmeQ5eTsSCXnKNSv8uKfost8Qos5f94xhbPqcniuGQxDTYnkKUK8GvPf_CKf6fnjuiogk2WBUZgQtNPt-0O2ZvxMOEJoAd4NGSG8hLaXIDNchehSmnALCSON6_L2XK1LrKNA")
        categoryImgUrlList.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQacDnAOM_PAnXy3L_Dwa6F6pzMwP3APctSXg&usqp=CAU")
        categoryImgUrlList.add("https://post-phinf.pstatic.net/MjAxNzA2MTNfMjE1/MDAxNDk3MzIyNjMzOTk2.eEFHHI_6A6XYBlYewiNUah56BAPIqKaLXIktBCnKseIg.rgtHIL_96v2Ok-kJ6mOI9q4vtn9ru4spCOClUyR7RvMg.JPEG/mug_obj_149732263448774583.jpg?type=w1080")
        categoryImgUrlList.add("https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F21622F3756405A970C")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
//        categoryImgUrlList.add("")
    }
}