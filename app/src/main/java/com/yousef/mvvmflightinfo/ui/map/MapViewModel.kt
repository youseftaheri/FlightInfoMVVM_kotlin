package com.yousef.mvvmflightinfo.ui.map

import androidx.lifecycle.MutableLiveData
import com.yousef.mvvmflightinfo.data.DataManager
import com.yousef.mvvmflightinfo.data.model.AirportLatLongPOJO
import com.yousef.mvvmflightinfo.ui.base.BaseViewModel
import com.yousef.mvvmflightinfo.utils.rx.SchedulerProvider

class MapViewModel (dataManager: DataManager?, schedulerProvider: SchedulerProvider?) : BaseViewModel<MapNavigator?>(dataManager!!, schedulerProvider!!) {
    private val originLiveData: MutableLiveData<String?> = MutableLiveData()
    private val destinationLiveData: MutableLiveData<String?> = MutableLiveData()

    init {
        originLiveData.value = dataManager!!.origin
        destinationLiveData.value = dataManager!!.destination
    }

    val coordinates: Unit
        get() {
        navigator!!.showLoading()
        dataManager.requestLatLong("Bearer " + dataManager.accessToken, dataManager.origin)
                ?.subscribeOn(schedulerProvider.io())
                ?.observeOn(schedulerProvider.ui())
                ?.subscribe({ data: AirportLatLongPOJO? ->
                    if (data != null) {
                        dataManager.orgLat = data.airportResource!!.Airports!!.Airport!!.Position!!.Coordinate!!.Latitude!!
                        dataManager.orgLng = data.airportResource!!.Airports!!.Airport!!.Position!!.Coordinate!!.Longitude!!

                        dataManager.requestLatLong("Bearer " + dataManager.accessToken, dataManager.destination)
                                ?.subscribeOn(schedulerProvider.io())
                                ?.observeOn(schedulerProvider.ui())
                                ?.subscribe({ data: AirportLatLongPOJO? ->
                                    navigator!!.hideLoading();
                                    if (data != null) {
                                        dataManager.dstLat = data.airportResource!!.Airports!!.Airport!!.Position!!.Coordinate!!.Latitude!!
                                        dataManager.dstLng = data.airportResource!!.Airports!!.Airport!!.Position!!.Coordinate!!.Longitude!!
                                        navigator!!.showData()
                                    } else navigator!!.handleError("Error occurred")
                                }, { throwable: Throwable ->
                                    navigator!!.hideLoading()
                                    navigator!!.handleError(throwable.message)
                                })?.let { compositeDisposable.add(it) }

                    } else navigator!!.handleError("Error occurred")
                }, { throwable: Throwable ->
                    navigator!!.hideLoading()
                    navigator!!.handleError(throwable.message)
                })?.let { compositeDisposable.add(it) }

    }

    fun getOriginLiveData(): MutableLiveData<String?> {
        return originLiveData
    }

    fun getDestinationLiveData(): MutableLiveData<String?> {
        return destinationLiveData
    }

    fun btBackClick(){
        navigator!!.btBackClick()
    }

    fun getOrigin(): String? {
        return dataManager.origin
    }

    fun getDestination(): String?{
        return dataManager.destination
    }

    fun getOrgLat(): String? {
        return dataManager.orgLat
    }

    fun getOrgLng(): String?{
        return dataManager.orgLng
    }

    fun getDstLat(): String? {
        return dataManager.dstLat
    }

    fun getDstLng(): String?{
        return dataManager.dstLng
    }

}