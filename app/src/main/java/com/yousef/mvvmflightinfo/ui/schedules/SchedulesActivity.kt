package com.yousef.mvvmflightinfo.ui.schedules

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yousef.mvvmflightinfo.BR
import com.yousef.mvvmflightinfo.R
import com.yousef.mvvmflightinfo.databinding.ActivitySchedulesBinding
import com.yousef.mvvmflightinfo.factory.ViewModelProviderFactory
import com.yousef.mvvmflightinfo.ui.base.BaseActivity
import com.yousef.mvvmflightinfo.ui.main.MainActivity
import com.yousef.mvvmflightinfo.ui.map.MapActivity
import com.yousef.mvvmflightinfo.utils.CommonUtils
import javax.inject.Inject

class SchedulesActivity : BaseActivity<ActivitySchedulesBinding, SchedulesViewModel?>(), SchedulesNavigator {
    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    @JvmField
    @Inject
    var mLayoutManager: LinearLayoutManager? = null

    @JvmField
    @Inject
    var mSchedulesAdapter: SchedulesAdapter? = null

    private var mActivitySchedulesBinding: ActivitySchedulesBinding? = null
    private var mSchedulesViewModel: SchedulesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSchedulesViewModel!!.setNavigator(this)
        mActivitySchedulesBinding = viewDataBinding
        mSchedulesViewModel!!.schedules
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_schedules

    override val viewModel: SchedulesViewModel
        get() {
            mSchedulesViewModel = ViewModelProvider(this, factory!!).get(SchedulesViewModel::class.java)
            return mSchedulesViewModel!!
        }

    override fun handleError(exception: String?) {
        Snackbar.make(mActivitySchedulesBinding!!.root, exception!!, Snackbar.LENGTH_LONG).show()
    }

    override fun setUp() {
        mLayoutManager!!.orientation = LinearLayoutManager.VERTICAL
        mActivitySchedulesBinding!!.recyclerView.layoutManager = mLayoutManager
        mActivitySchedulesBinding!!.recyclerView.itemAnimator = DefaultItemAnimator()
        mActivitySchedulesBinding!!.recyclerView.adapter = SchedulesAdapter(mSchedulesViewModel!!.getScheduleItemsLiveData()!!.value)

        mActivitySchedulesBinding!!.swipeRefresh.setOnRefreshListener {
            mActivitySchedulesBinding!!.swipeRefresh.isRefreshing = false
            mSchedulesViewModel!!.schedules
        }
    }

    override fun btBackClick() {
        onBackPressed()
    }

    fun onDetailsClick() {
        startNewActivity(MapActivity::class.java, isFinishAll = false, isCurrentFinish = false)
    }

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, SchedulesActivity::class.java)
        }
    }
}