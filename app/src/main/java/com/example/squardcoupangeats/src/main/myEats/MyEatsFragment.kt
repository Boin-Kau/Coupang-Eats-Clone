package com.example.squardcoupangeats.src.main.myEats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.squardcoupangeats.R
import com.example.squardcoupangeats.config.BaseFragment
import com.example.squardcoupangeats.databinding.FragmentMyEatsBinding
import com.kakao.sdk.user.UserApiClient

class MyEatsFragment : BaseFragment<FragmentMyEatsBinding>(FragmentMyEatsBinding::bind, R.layout.fragment_my_eats) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInfo()
    }




    private fun setUserInfo() {
        UserApiClient.instance.me { user, error ->
            user?.let {
                binding.myEatsFragUserName.text = user.kakaoAccount?.profile?.nickname
                binding.myEatsFragUserPhoneNumber.text = user.kakaoAccount?.email
            }
            error?.let {
                binding.myEatsFragUserName.text = null
                binding.myEatsFragUserPhoneNumber.text = null
            }
        }
    }

}