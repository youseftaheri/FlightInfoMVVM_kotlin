package com.yousef.mvvmflightinfo.ui.schedules

import com.yousef.mvvmflightinfo.data.model.LuftSchedulesPOJO

interface SchedulesNavigator {
    fun handleError(exception: String?)
    fun showLoading()
    fun hideLoading()
    fun btBackClick()
    fun setUp()
}