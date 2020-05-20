package com.yousef.mvvmflightinfo.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.yousef.mvvmflightinfo.data.DataManager
import com.yousef.mvvmflightinfo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(val dataManager: DataManager,
                                val schedulerProvider: SchedulerProvider) : ViewModel() {
    val isLoading = ObservableBoolean()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var mNavigator: WeakReference<N>? = null
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

//    fun setIsLoading(isLoading: Boolean) {
//        isLoading[] = isLoading
//    }

    val navigator: N?
        get() = mNavigator!!.get()

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }

}