package com.yousef.mvvmflightinfo.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.yousef.mvvmflightinfo.di.PreferenceInfo
import javax.inject.Inject


class AppPreferencesHelper @Inject constructor(context: Context, @PreferenceInfo prefFileName: String?) : PreferencesHelper {
    private val mPrefs: SharedPreferences

    /**
     * Clear SharedPreferences (remove everything)
     */
    override fun clear() {
        mPrefs.edit().clear().apply()
    }

    override var accessToken: String?
        get() = mPrefs.getString(ACCESS_TOKEN, null)
        set(data) {
            mPrefs.edit().putString(ACCESS_TOKEN, data).apply()
        }

    override var origin: String?
        get() = mPrefs.getString(ORIGIN, null)
        set(data) {
            mPrefs.edit().putString(ORIGIN, data).apply()
        }

    override var destination: String?
        get() = mPrefs.getString(DESTINATION, null)
        set(data) {
            mPrefs.edit().putString(DESTINATION, data).apply()
        }

    override var orgLat: String?
        get() = mPrefs.getString(ORG_LAT, null)
        set(data) {
            mPrefs.edit().putString(ORG_LAT, data).apply()
        }

    override var orgLng: String?
        get() = mPrefs.getString(ORG_LNG, null)
        set(data) {
            mPrefs.edit().putString(ORG_LNG, data).apply()
        }

    override var dstLat: String?
        get() = mPrefs.getString(DST_LAT, null)
        set(data) {
            mPrefs.edit().putString(DST_LAT, data).apply()
        }

    override var dstLng: String?
        get() = mPrefs.getString(DST_LNG, null)
        set(data) {
            mPrefs.edit().putString(DST_LNG, data).apply()
        }

    companion object {
        private const val MAIN_BASE = "https://api.lufthansa.com/"
        var BASE_URL = MAIN_BASE + "v1/"
        var DST_LAT = "DST_LAT"
        var DST_LNG = "DST_LNG"
        var ORG_LAT = "ORG_LAT"
        var ORG_LNG = "ORG_LNG"
        var ORIGIN = "ORIGIN"
        var DESTINATION = "DESTINATION"
        var ACCESS_TOKEN = "ACCESS_TOKEN"
        const val TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss"
    }

    init {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }
}