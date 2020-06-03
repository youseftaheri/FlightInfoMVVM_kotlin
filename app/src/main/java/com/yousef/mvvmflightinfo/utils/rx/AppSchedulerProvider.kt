package com.yousef.mvvmflightinfo.utils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

class AppSchedulerProvider : SchedulerProvider {
    override fun ui(): Scheduler? {
        return AndroidSchedulers.mainThread()
    }

    override fun computation(): Scheduler? {
        return Schedulers.computation()
    }

    override fun io(): Scheduler? {
        return Schedulers.io()
    }
}


class TrampolineSchedulerProvider : SchedulerProvider {
    override fun computation() = Schedulers.trampoline()
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}

class TestSchedulerProvider(private val scheduler: TestScheduler) : SchedulerProvider {
    override fun computation() = scheduler
    override fun ui() = scheduler
    override fun io() = scheduler
}