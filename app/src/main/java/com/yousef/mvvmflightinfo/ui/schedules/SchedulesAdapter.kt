package com.yousef.mvvmflightinfo.ui.schedules

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yousef.mvvmflightinfo.data.model.LuftSchedulesPOJO
import com.yousef.mvvmflightinfo.databinding.ItemScheduleViewBinding
import com.yousef.mvvmflightinfo.ui.base.BaseAdapter
import com.yousef.mvvmflightinfo.ui.base.BaseViewHolder

class SchedulesAdapter(scheduleItemViewModelList: List<LuftSchedulesPOJO.Schedule?>?) : BaseAdapter<LuftSchedulesPOJO.Schedule?>() {
    override fun onBindViewHolderBase(holder: BaseViewHolder?, position: Int) {
        holder!!.onBind(position)
    }

    init {
        dataList = scheduleItemViewModelList
    }
    
    override fun onCreateViewHolderBase(parent: ViewGroup?, viewType: Int): BaseViewHolder {
        val mScheduleViewBinding = ItemScheduleViewBinding
                .inflate(LayoutInflater.from(parent!!.context), parent, false)
        return ScheduleViewHolder(mScheduleViewBinding)
    }

    inner class ScheduleViewHolder(var mBinding: ItemScheduleViewBinding) : BaseViewHolder(mBinding.root), ScheduleItemViewModel.ScheduleItemViewModelListener {
        private var scheduleItemViewModel: ScheduleItemViewModel? = null
        override fun onBind(position: Int) {
            val schedule = dataList!![position]!!
            scheduleItemViewModel = ScheduleItemViewModel(schedule, this)
            mBinding.viewModel = scheduleItemViewModel
            mBinding.executePendingBindings()
        }

        override fun onDetailsClick() {
            (itemView.context as SchedulesActivity).onDetailsClick()
        }

    }
}