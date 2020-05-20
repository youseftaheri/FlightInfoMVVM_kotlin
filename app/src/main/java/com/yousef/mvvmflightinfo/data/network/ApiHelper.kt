package com.yousef.mvvmflightinfo.data.network

import com.yousef.mvvmflightinfo.data.model.AirportLatLongPOJO
import com.yousef.mvvmflightinfo.data.model.AirportsPOJO
import com.yousef.mvvmflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvvmflightinfo.data.model.TokenPOJO
import io.reactivex.Single

interface ApiHelper {
    fun requestAccessTokenPost(): Single<TokenPOJO?>
    fun requestLuftSchedules(token: String?, date: String?): Single<LuftSchedulesPOJO?>
    fun requestAirports(token: String?): Single<AirportsPOJO?>?
    fun requestLatLong(token: String?, airport: String?): Single<AirportLatLongPOJO?>
}