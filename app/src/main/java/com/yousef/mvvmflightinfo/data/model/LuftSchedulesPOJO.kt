package com.yousef.mvvmflightinfo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LuftSchedulesPOJO {
    constructor()

    constructor(schedules: List<Schedule>) {
        scheduleResource = ScheduleResource(schedules)
    }

    constructor(resource: ScheduleResource){
        scheduleResource = resource
    }

    @JvmField
    @Expose
    @SerializedName("ScheduleResource")
    var scheduleResource: ScheduleResource? = null

    inner class ScheduleResource {
        constructor()

        constructor(schedules: List<Schedule>) {
            Schedules = schedules
        }

        @JvmField
        @Expose
        @SerializedName("Schedule")
        var Schedules: List<Schedule>? = null
    }

    inner class Schedule : Comparable<Schedule> {
        constructor()

        constructor(flight: Flight){
//            TotalJourney = totalJourney
            Flight = flight
        }

        @JvmField
        @Expose
        @SerializedName("TotalJourney")
        var TotalJourney: TotalJourney? = null

        @JvmField
        @Expose
        @SerializedName("Flight")
        var Flight: Flight? = null
        override fun compareTo(schedule: Schedule): Int {
            return schedule.Flight!!.Departure!!.ScheduledTimeLocal!!.DateTime!!.compareTo(Flight!!.Departure!!.ScheduledTimeLocal!!.DateTime!!, ignoreCase = true)
        }
    }

    inner class TotalJourney {
        constructor()

        constructor(duration: String){
            Duration = duration
        }

        @JvmField
        @Expose
        @SerializedName("Duration")
        var Duration: String? = null
    }

    inner class Flight {
        constructor()

        constructor(marketingCarrier: MarketingCarrier){
//            Departure = departure
//            Arrival = arrival
//            Details = details
            MarketingCarrier = marketingCarrier
        }

        @JvmField
        @Expose
        @SerializedName("Departure")
        var Departure: Dep_Arr? = null

        @JvmField
        @Expose
        @SerializedName("Arrival")
        var Arrival: Dep_Arr? = null

        @JvmField
        @Expose
        @SerializedName("Details")
        var Details: Details? = null

        @JvmField
        @Expose
        @SerializedName("MarketingCarrier")
        var MarketingCarrier: MarketingCarrier? = null
    }

    inner class MarketingCarrier {
        constructor()

        constructor(flightNumber: String){
            FlightNumber = flightNumber
        }

        @JvmField
        @Expose
        @SerializedName("FlightNumber")
        var FlightNumber: String? = null
    }

    inner class Dep_Arr {
        @JvmField
        @Expose
        @SerializedName("AirportCode")
        var AirportCode: String? = null

        @JvmField
        @Expose
        @SerializedName("ScheduledTimeLocal")
        var ScheduledTimeLocal: ScheduledTimeLocal? = null

        @JvmField
        @Expose
        @SerializedName("Terminal")
        var Terminal: Terminal? = null
    }

    inner class ScheduledTimeLocal {
        @JvmField
        @Expose
        @SerializedName("DateTime")
        var DateTime: String? = null
    }

    inner class Terminal {
        @JvmField
        @Expose
        @SerializedName("Name")
        var Name: String? = null
    }

    inner class Details {
        @JvmField
        @Expose
        @SerializedName("Stops")
        var Stops: Stops? = null
    }

    inner class Stops {
        @JvmField
        @Expose
        @SerializedName("StopQuantity")
        var StopQuantity: String? = null
    }

}