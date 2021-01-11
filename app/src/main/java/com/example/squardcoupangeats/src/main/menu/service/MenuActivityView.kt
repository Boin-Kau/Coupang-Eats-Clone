package com.example.squardcoupangeats.src.main.menu.service

import com.example.squardcoupangeats.src.main.menu.models.SpecificMenuResponse

interface MenuActivityView {

    fun onGetSpecificMenuSuccess(response: SpecificMenuResponse)

    fun onGetSpecificMenuFailure(message: String)
}