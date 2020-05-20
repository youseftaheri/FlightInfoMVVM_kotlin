package com.yousef.mvvmflightinfo.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LuftSchedulesPOJO {
    @JvmField
    @Expose
    @SerializedName("ScheduleResource")
    var scheduleResource: ScheduleResource? = null

    class ScheduleResource {
        @JvmField
        @Expose
        @SerializedName("Schedule")
        var Schedules: List<Schedule>? = null
    }

    class Schedule : Comparable<Schedule> {
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

    class TotalJourney {
        @JvmField
        @Expose
        @SerializedName("Duration")
        var Duration: String? = null
    }

    class Flight {
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

    class MarketingCarrier {
        @JvmField
        @Expose
        @SerializedName("FlightNumber")
        var FlightNumber: String? = null
    }

    class Dep_Arr {
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

    class ScheduledTimeLocal {
        @JvmField
        @Expose
        @SerializedName("DateTime")
        var DateTime: String? = null
    }

    class Terminal {
        @JvmField
        @Expose
        @SerializedName("Name")
        var Name: String? = null
    }

    class Details {
        @JvmField
        @Expose
        @SerializedName("Stops")
        var Stops: Stops? = null
    }

    class Stops {
        @JvmField
        @Expose
        @SerializedName("StopQuantity")
        var StopQuantity: String? = null
    }
}