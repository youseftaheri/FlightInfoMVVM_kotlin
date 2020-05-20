package com.yousef.mvvmflightinfo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yousef.mvvmflightinfo.data.DataManager
import com.yousef.mvvmflightinfo.ui.main.MainViewModel
import com.yousef.mvvmflightinfo.ui.map.MapViewModel
import com.yousef.mvvmflightinfo.ui.schedules.SchedulesViewModel
import com.yousef.mvvmflightinfo.ui.splash.SplashViewModel
import com.yousef.mvvmflightinfo.utils.rx.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelProviderFactory @Inject constructor(private val dataManager: DataManager,
                                                   private val schedulerProvider: SchedulerProvider) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(dataManager, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(dataManager, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(SchedulesViewModel::class.java) -> {
                SchedulesViewModel(dataManager, schedulerProvider) as T
            }
            modelClass.isAssignableFrom(MapViewModel::class.java) -> {
                MapViewModel(dataManager, schedulerProvider) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

}