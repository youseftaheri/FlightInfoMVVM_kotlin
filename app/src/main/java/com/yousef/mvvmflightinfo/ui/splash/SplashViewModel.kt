package com.yousef.mvvmflightinfo.ui.splash

import com.yousef.mvvmflightinfo.data.DataManager
import com.yousef.mvvmflightinfo.data.model.TokenPOJO
import com.yousef.mvvmflightinfo.ui.base.BaseViewModel
import com.yousef.mvvmflightinfo.utils.rx.SchedulerProvider

class SplashViewModel (dataManager: DataManager?, schedulerProvider: SchedulerProvider?) : BaseViewModel<SplashNavigator?>(dataManager!!, schedulerProvider!!) {
    fun requestAccessTokenPost() {
        navigator!!.showLoading()
        dataManager.requestAccessTokenPost()
                ?.subscribeOn(schedulerProvider.io())
                ?.observeOn(schedulerProvider.ui())
                ?.subscribe({ data: TokenPOJO? ->
                    navigator!!.hideLoading();
                    if (data != null && data.access_token != null) {
                        dataManager.accessToken = data!!.access_token!!
                        navigator!!.openMainActivity()
                    } else navigator!!.handleError("Error occurred")
                }, { throwable: Throwable ->
                    navigator!!.hideLoading()
                    navigator!!.handleError(throwable.message)
                })?.let { compositeDisposable.add(it) }
    }
}