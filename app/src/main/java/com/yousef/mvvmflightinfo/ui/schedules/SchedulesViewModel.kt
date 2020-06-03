package com.yousef.mvvmflightinfo.ui.schedules

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yousef.mvvmflightinfo.data.DataManager
import com.yousef.mvvmflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvvmflightinfo.ui.base.BaseViewModel
import com.yousef.mvvmflightinfo.utils.rx.SchedulerProvider
import java.text.SimpleDateFormat
import java.util.*

class SchedulesViewModel (dataManager: DataManager?, schedulerProvider: SchedulerProvider?) : BaseViewModel<SchedulesNavigator?>(dataManager!!, schedulerProvider!!) {
    private val originLiveData: MutableLiveData<String?>
    private val destinationLiveData: MutableLiveData<String?>
    private val scheduleItemsLiveData: MutableLiveData<List<LuftSchedulesPOJO.Schedule>?>?

    init {
        scheduleItemsLiveData = MutableLiveData()
        originLiveData = MutableLiveData()
        destinationLiveData = MutableLiveData()
        originLiveData.value = dataManager!!.origin
        destinationLiveData.value = dataManager!!.destination
    }

    val schedules: Unit
        get() {
            navigator!!.showLoading()
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            dataManager.requestLuftSchedules("Bearer " + dataManager.accessToken,  date)
                    ?.subscribeOn(schedulerProvider.io())
                    ?.observeOn(schedulerProvider.ui())
                    ?.subscribe({ data: LuftSchedulesPOJO? ->
                        navigator!!.hideLoading();
                        if (data != null) {
                            if (data.scheduleResource!!.Schedules!!.isEmpty()) {
                                navigator!!.handleError("NO FLIGHT FOUND")
                                btBackClick()
                            } else {
                                scheduleItemsLiveData?.value = data.scheduleResource!!.Schedules
                                navigator!!.setUp()
                            }
                        } else navigator!!.handleError("Error occurred")
                    }, { throwable: Throwable ->
                        navigator!!.hideLoading()
                        navigator!!.handleError(throwable.message)
                    })?.let { compositeDisposable.add(it) }
        }

    fun btBackClick() {
        navigator!!.btBackClick()
    }

    fun getScheduleItemsLiveData(): MutableLiveData<List<LuftSchedulesPOJO.Schedule>?>? {
        return scheduleItemsLiveData
    }

    fun getOriginLiveData(): LiveData<String?> {
        return originLiveData
    }

    fun getDestinationLiveData(): LiveData<String?> {
        return destinationLiveData
    }
}