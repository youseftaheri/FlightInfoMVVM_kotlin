package com.yousef.mvvmflightinfo.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.yousef.mvvmflightinfo.BR
import com.yousef.mvvmflightinfo.R
import com.yousef.mvvmflightinfo.data.model.AirportsPOJO
import com.yousef.mvvmflightinfo.databinding.ActivityMainBinding
import com.yousef.mvvmflightinfo.factory.ViewModelProviderFactory
import com.yousef.mvvmflightinfo.ui.base.BaseActivity
import com.yousef.mvvmflightinfo.ui.schedules.SchedulesActivity
import com.yousef.mvvmflightinfo.utils.CommonUtils
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel?>(), MainNavigator {
    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null
    private var mActivityMainBinding: ActivityMainBinding? = null
    private var mMainViewModel: MainViewModel? = null
    var airportNameStr: MutableList<String>? = null
    var airports: List<AirportsPOJO.Airport?>? = null
    var airportIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainViewModel!!.setNavigator(this)
        mActivityMainBinding = viewDataBinding
        mMainViewModel!!.loadData()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() {
            mMainViewModel = ViewModelProvider(this, factory!!).get(MainViewModel::class.java)
            return mMainViewModel!!
        }

    override fun handleError(exception: String?) {
        Snackbar.make(mActivityMainBinding!!.root, exception!!, Snackbar.LENGTH_LONG).show()
    }

    override fun btShowSchedulesClick() {
        if (mActivityMainBinding!!.etOrigin.text.toString().isEmpty())
            handleError(getString(R.string.enter_origin))
        else if (mActivityMainBinding!!.etDestination.text.toString().isEmpty())
            handleError(getString(R.string.enter_destination))
        else {
            airportIndex = find(airportNameStr, mActivityMainBinding!!.etOrigin.text.toString())
            if (airportIndex > -1)
                mMainViewModel!!.setOrigin(airports!![airportIndex]!!.AirportCode!!)
            else
                mMainViewModel!!.setOrigin(mActivityMainBinding!!.etOrigin.text.toString())
            airportIndex = find(airportNameStr, mActivityMainBinding!!.etDestination.text.toString())
            if (airportIndex > -1)
                mMainViewModel!!.setDestination(airports!![airportIndex]!!.AirportCode!!)
            else
                mMainViewModel!!.setDestination(mActivityMainBinding!!.etDestination.text.toString())
            startNewActivity(SchedulesActivity::class.java, isFinishAll = false, isCurrentFinish = false)
        }
    }

    override fun ivClearOriginClick() {
        mActivityMainBinding!!.etOrigin.setText("")
    }

    override fun ivClearDestinationClick() {
        mActivityMainBinding!!.etDestination.setText("")
    }

    override fun showData(data: AirportsPOJO) {
        airports = data.airportResource!!.Airports!!.Airport
        setAutoStrings()
    }

    fun setAutoStrings() {
        airportNameStr = ArrayList()
        for (airport in airports!!) {
            (airportNameStr as ArrayList<String>).add(airport!!.Names!!.Name!!.fullName + " - " + airport.CityCode + " - " + airport.CountryCode)
        }
        val originAdp = ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, airportNameStr as ArrayList<String>)
        mActivityMainBinding!!.etOrigin.threshold = 1
        mActivityMainBinding!!.etOrigin.setAdapter(originAdp)
        val destinationAdp = ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, airportNameStr as ArrayList<String>)
        mActivityMainBinding!!.etDestination.threshold = 1
        mActivityMainBinding!!.etDestination.setAdapter(destinationAdp)
    }

    fun find(a: List<String>?, target: String): Int {
        for (i in a!!.indices) if (target == a[i]) return i
        return -1
    }

    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}