package com.yousef.mvvmflightinfo.ui.splash

import com.yousef.mvvmflightinfo.data.model.TokenPOJO

interface SplashNavigator {
    fun handleError(exception: String?)
    fun showLoading()
    fun hideLoading()
    fun openMainActivity()
}