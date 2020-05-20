package com.yousef.mvvmflightinfo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AirportsPOJO {
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
        var Airport: List<Airport>? = null
    }

    class Airport {
        @JvmField
        @Expose
        @SerializedName("AirportCode")
        var AirportCode: String? = null

        @JvmField
        @Expose
        @SerializedName("CityCode")
        var CityCode: String? = null

        @JvmField
        @Expose
        @SerializedName("CountryCode")
        var CountryCode: String? = null

        @JvmField
        @Expose
        @SerializedName("Position")
        var Position: Position? = null

        @JvmField
        @Expose
        @SerializedName("Names")
        var Names: Name? = null
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

    class Name {
        @JvmField
        @Expose
        @SerializedName("Name")
        var Name: AirportName? = null
    }

    class AirportName {
        @JvmField
        @Expose
        @SerializedName("$")
        var fullName: String? = null
    }
}