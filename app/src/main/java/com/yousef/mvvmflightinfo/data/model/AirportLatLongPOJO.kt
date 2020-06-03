package com.yousef.mvvmflightinfo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AirportLatLongPOJO {
    constructor()
    constructor(resource: AirportResource) {
        airportResource = resource
    }

    @JvmField
    @Expose
    @SerializedName("AirportResource")
    var airportResource: AirportResource? = null

    inner class AirportResource {
        constructor()
        constructor(airports: Airports) {
            Airports = airports
        }

        @JvmField
        @Expose
        @SerializedName("Airports")
        var Airports: Airports? = null
    }

    inner class Airports {
        constructor()
        constructor(airport: Airport) {
            Airport = airport
        }

        @JvmField
        @Expose
        @SerializedName("Airport")
        var Airport: Airport? = null
    }

    inner class Airport {
        constructor()
        constructor(position: Position) {
            Position = position
        }

        @JvmField
        @Expose
        @SerializedName("Position")
        var Position: Position? = null
    }

    inner class Position {
        constructor()
        constructor(coordinate: Coordinate) {
            Coordinate = coordinate
        }

        @JvmField
        @Expose
        @SerializedName("Coordinate")
        var Coordinate: Coordinate? = null
    }

    inner class Coordinate {
        constructor()
        constructor(latitude: String, longitude: String) {
            Latitude = latitude
            Longitude = longitude
        }

        @JvmField
        @Expose
        @SerializedName("Latitude")
        var Latitude: String? = null

        @JvmField
        @Expose
        @SerializedName("Longitude")
        var Longitude: String? = null

        fun setLatLng(latitude: String, longitude: String) {
            Latitude = latitude
            Longitude = longitude
        }
    }
}