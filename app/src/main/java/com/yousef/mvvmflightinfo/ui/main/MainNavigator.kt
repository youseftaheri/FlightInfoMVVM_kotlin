package com.yousef.mvvmflightinfo.ui.main

import com.yousef.mvvmflightinfo.data.model.AirportsPOJO

interface MainNavigator {
    fun showData(data: AirportsPOJO)
    fun handleError(exception: String?)
    fun showLoading()
    fun hideLoading()
    fun btShowSchedulesClick()
    fun ivClearOriginClick()
    fun ivClearDestinationClick()
}