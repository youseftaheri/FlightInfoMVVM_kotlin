package com.yousef.mvvmflightinfo.di.module

import android.app.Application
import android.content.Context
import com.yousef.mvvmflightinfo.data.AppDataManager
import com.yousef.mvvmflightinfo.data.DataManager
import com.yousef.mvvmflightinfo.data.network.ApiHelper
import com.yousef.mvvmflightinfo.data.network.AppApiHelper
import com.yousef.mvvmflightinfo.data.preferences.AppPreferencesHelper
import com.yousef.mvvmflightinfo.data.preferences.PreferencesHelper
import com.yousef.mvvmflightinfo.di.PreferenceInfo
import com.yousef.mvvmflightinfo.utils.CommonUtils
import com.yousef.mvvmflightinfo.utils.rx.AppSchedulerProvider
import com.yousef.mvvmflightinfo.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import io.reactivex.disposables.CompositeDisposable


@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: AppPreferencesHelper): PreferencesHelper = appPreferenceHelper

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return CommonUtils.PREF_NAME
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Singleton
    @Provides
    fun provideUtils(context: Context?): CommonUtils {
        return CommonUtils
    }

}