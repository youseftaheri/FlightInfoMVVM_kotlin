package com.yousef.mvvmflightinfo.utils

import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yousef.mvvmflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvvmflightinfo.ui.schedules.SchedulesAdapter

object BindingUtils {
    @JvmStatic
    @BindingAdapter("adapter")
    fun addSchedulesItems(recyclerView: RecyclerView, schedulesItems: List<LuftSchedulesPOJO.Schedule?>?) {
        val adapter = SchedulesAdapter(schedulesItems)
        recyclerView.adapter = adapter
    }

    @BindingAdapter("text")
    fun bindtext(tv: TextView, value: Int) {
        tv.text = value.toString()
    }

    @JvmStatic
    @BindingAdapter("textChangedListener")
    fun bindTextWatcher(editText: EditText, textWatcher: TextWatcher?) {
        editText.addTextChangedListener(textWatcher)
    }

}