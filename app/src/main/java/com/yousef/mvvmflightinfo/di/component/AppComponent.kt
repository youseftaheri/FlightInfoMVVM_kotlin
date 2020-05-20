package com.yousef.mvvmflightinfo.di.component

import android.app.Application
import com.yousef.mvvmflightinfo.MyApplication
import com.yousef.mvvmflightinfo.di.builder.ActivityBuilder
import com.yousef.mvvmflightinfo.di.module.ApiModule
import com.yousef.mvvmflightinfo.di.module.AppModule
//import com.yousef.mvpflightinfo.ui.schedules.SchedulesAdapterModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ApiModule::class, ActivityBuilder::class])
interface AppComponent : AndroidInjector<DaggerApplication?> {
    fun inject(app: MyApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder?
        fun build(): AppComponent?
    }
}