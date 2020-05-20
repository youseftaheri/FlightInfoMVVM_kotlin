package com.yousef.mvvmflightinfo.ui.main

import com.yousef.mvvmflightinfo.data.DataManager
import com.yousef.mvvmflightinfo.data.model.AirportsPOJO
import com.yousef.mvvmflightinfo.ui.base.BaseViewModel
import com.yousef.mvvmflightinfo.utils.rx.SchedulerProvider

class MainViewModel (dataManager: DataManager?, schedulerProvider: SchedulerProvider?) : BaseViewModel<MainNavigator?>(dataManager!!, schedulerProvider!!) {
    fun setOrigin(origin: String) {
        dataManager.origin = origin
    }

    fun setDestination(destination: String) {
        dataManager.destination = destination
    }

    fun loadData() {
        navigator!!.showLoading()
        dataManager.requestAirports("Bearer " + dataManager!!.accessToken)
                ?.subscribeOn(schedulerProvider.io())
                ?.observeOn(schedulerProvider.ui())
                ?.subscribe({ data: AirportsPOJO? ->
                    navigator!!.hideLoading()
                    if (data != null) {
                        navigator!!.showData(data!!)
                    } else navigator!!.handleError("Error occurred")
                }, { throwable: Throwable ->
                    navigator!!.hideLoading()
                    navigator!!.handleError(throwable.message)
                })?.let { compositeDisposable.add(it) }

    }

    fun btShowSchedulesClick() {
        navigator!!.btShowSchedulesClick()
    }

    fun ivClearOriginClick() {
        navigator!!.ivClearOriginClick()
    }

    fun ivClearDestinationClick() {
        navigator!!.ivClearDestinationClick()
    }
}