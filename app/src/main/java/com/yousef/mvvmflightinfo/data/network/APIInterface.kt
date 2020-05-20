package com.yousef.mvvmflightinfo.data.network

import com.yousef.mvvmflightinfo.data.model.AirportLatLongPOJO
import com.yousef.mvvmflightinfo.data.model.AirportsPOJO
import com.yousef.mvvmflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvvmflightinfo.data.model.TokenPOJO
import io.reactivex.Single
import retrofit2.http.*
import java.util.*

interface APIInterface {
    @FormUrlEncoded
    @POST("v1/oauth/token")
    fun requestAccessTokenPost(@FieldMap map: HashMap<String?, String?>): Single<TokenPOJO?>

    @GET("v1/operations/schedules/{origin}/{destination}/{date}?directFlights=1&limit=100")
    fun requestLuftSchedules(@Header("Authorization") token: String?,
                             @Path("origin") origin: String?,
                             @Path("destination") destination: String?,
                             @Path("date") date: String?): Single<LuftSchedulesPOJO?>

    @GET("v1/references/airports/?lang=en&limit=100&LHoperated=1&offset=410")
    fun requestAirports(@Header("Authorization") token: String?): Single<AirportsPOJO?>?

    @GET("v1/references/airports/{airport}?lang=en&limit=100&LHoperated=1&offset=410")
    fun requestLatLong(@Header("Authorization") token: String?,
                       @Path("airport") airport: String?): Single<AirportLatLongPOJO?>
}