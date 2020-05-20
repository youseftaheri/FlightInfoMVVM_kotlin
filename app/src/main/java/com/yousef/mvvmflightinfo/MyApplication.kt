package com.yousef.mvvmflightinfo

import android.app.Activity
import android.app.Application
import com.yousef.mvvmflightinfo.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

        @Inject
        lateinit internal var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


        override fun activityInjector() = activityDispatchingAndroidInjector

        override fun onCreate() {
            super.onCreate()
            DaggerAppComponent.builder()
                    .application(this)
                    ?.build()
                    ?.inject(this)
        }

    }