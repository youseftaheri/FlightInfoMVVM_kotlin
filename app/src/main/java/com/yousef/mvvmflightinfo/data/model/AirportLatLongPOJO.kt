package com.yousef.mvvmflightinfo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AirportLatLongPOJO {
    @JvmField
    @Expose
    @SerializedName("AirportResource")
    var airportResource: AirportResource? = null

    class AirportResource {
        @JvmField
        @Expose
        @SerializedName("Airports")
        var Airports: Airports? = null
    }

    class Airports {
        @JvmField
        @Expose
        @SerializedName("Airport")
        var Airport: Airport? = null
    }

    class Airport {
        @JvmField
        @Expose
        @SerializedName("Position")
        var Position: Position? = null
    }

    class Position {
        @JvmField
        @Expose
        @SerializedName("Coordinate")
        var Coordinate: Coordinate? = null
    }

    class Coordinate {
        @JvmField
        @Expose
        @SerializedName("Latitude")
        var Latitude: String? = null

        @JvmField
        @Expose
        @SerializedName("Longitude")
        var Longitude: String? = null
    }
}