package com.yousef.mvvmflightinfo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yousef.mvvmflightinfo.di.ViewModelKey
import com.yousef.mvvmflightinfo.factory.ViewModelProviderFactory
import com.yousef.mvvmflightinfo.ui.main.MainViewModel
import com.yousef.mvvmflightinfo.ui.map.MapViewModel
import com.yousef.mvvmflightinfo.ui.schedules.SchedulesViewModel
import com.yousef.mvvmflightinfo.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(SchedulesViewModel::class)
    abstract fun bindSchedulesViewModel(schedulesViewModel: SchedulesViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    abstract fun bindMapViewModel(mapViewModel: MapViewModel?): ViewModel?

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory?): ViewModelProvider.Factory?
}