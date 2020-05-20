package com.yousef.mvvmflightinfo.di.builder

import com.yousef.mvvmflightinfo.ui.main.MainActivity
import com.yousef.mvvmflightinfo.ui.map.MapActivity
import com.yousef.mvvmflightinfo.ui.schedules.SchedulesActivity
import com.yousef.mvvmflightinfo.ui.schedules.SchedulesModule
import com.yousef.mvvmflightinfo.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [SchedulesModule::class])
    abstract fun bindSchedulesActivity(): SchedulesActivity

    @ContributesAndroidInjector
    abstract fun bindMapActivity(): MapActivity
}