package com.yousef.mvvmflightinfo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TokenPOJO {
    @JvmField
    @Expose
    @SerializedName("access_token")
    var access_token: String? = null

    fun setAccessToken(token: String) {
        access_token = token
    }

}