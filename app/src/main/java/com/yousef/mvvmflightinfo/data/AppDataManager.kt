package com.yousef.mvvmflightinfo.data
import android.content.Context
import com.yousef.mvvmflightinfo.data.model.AirportLatLongPOJO
import com.yousef.mvvmflightinfo.data.model.AirportsPOJO
import com.yousef.mvvmflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvvmflightinfo.data.model.TokenPOJO
import com.yousef.mvvmflightinfo.data.network.ApiHelper
import com.yousef.mvvmflightinfo.data.preferences.PreferencesHelper
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager
@Inject
constructor(private val mContext: Context, private val mPreferencesHelper: PreferencesHelper,
            private val mApiHelper: ApiHelper) : DataManager {

    override fun clear() {
        mPreferencesHelper.clear()
    }

    override var accessToken: String?
        get() = mPreferencesHelper.accessToken
        set(data) {
            mPreferencesHelper.accessToken = data
        }

    override var origin: String?
        get() = mPreferencesHelper.origin
        set(data) {
            mPreferencesHelper.origin = data
        }

    override var destination: String?
        get() = mPreferencesHelper.destination
        set(data) {
            mPreferencesHelper.destination = data
        }

    override var orgLat: String?
        get() = mPreferencesHelper.orgLat
        set(data) {
            mPreferencesHelper.orgLat = data
        }

    override var orgLng: String?
        get() = mPreferencesHelper.orgLng
        set(data) {
            mPreferencesHelper.orgLng = data
        }

    override var dstLat: String?
        get() = mPreferencesHelper.dstLat
        set(data) {
            mPreferencesHelper.dstLat = data
        }

    override var dstLng: String?
        get() = mPreferencesHelper.dstLng
        set(data) {
            mPreferencesHelper.dstLng = data
        }

    override fun requestAccessTokenPost(): Single<TokenPOJO?> {
        return mApiHelper.requestAccessTokenPost()
    }

    override fun requestLuftSchedules(token: String?, date: String?): Single<LuftSchedulesPOJO?> {
        return mApiHelper.requestLuftSchedules(token, date)
    }

    override fun requestAirports(token: String?): Single<AirportsPOJO?>? {
        return mApiHelper.requestAirports(token)
    }

    override fun requestLatLong(token: String?, airport: String?): Single<AirportLatLongPOJO?> {
        return mApiHelper.requestLatLong(token, airport)
    }

    companion object {
        private const val TAG = "AppDataManager"
    }


}