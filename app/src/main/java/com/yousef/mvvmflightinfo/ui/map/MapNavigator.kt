package com.yousef.mvvmflightinfo.ui.map

interface MapNavigator {
    fun showData()
    fun handleError(exception: String?)
    fun showLoading()
    fun hideLoading()
    fun btBackClick()
}