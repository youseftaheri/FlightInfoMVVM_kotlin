package com.yousef.mvvmflightinfo.ui.schedules

import androidx.databinding.ObservableField
import com.yousef.mvvmflightinfo.data.model.LuftSchedulesPOJO

class ScheduleItemViewModel (private val mSchedule: LuftSchedulesPOJO.Schedule,
                             private val mListener: ScheduleItemViewModelListener) {
    @JvmField
    val flightNumber: ObservableField<String> = ObservableField("FlightNumber: " + mSchedule.Flight!!.MarketingCarrier!!.FlightNumber)

    @JvmField
    val departureDate: ObservableField<String> = ObservableField(mSchedule.Flight!!.Departure!!.ScheduledTimeLocal!!.DateTime!!.replace("T", "  "))

    @JvmField
    val arrivalDate: ObservableField<String?> = ObservableField(mSchedule.Flight!!.Arrival!!.ScheduledTimeLocal!!.DateTime!!.replace("T", "  "))

    fun onDetailsClick() {
        mListener.onDetailsClick()
    }
    
    interface ScheduleItemViewModelListener {
        fun onDetailsClick()
    }
}