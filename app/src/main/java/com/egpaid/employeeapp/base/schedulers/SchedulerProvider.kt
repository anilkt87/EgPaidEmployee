package com.egpaid.employeeapp.base.base.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : BaseSchedulerProvider {
    override fun computation(): Scheduler = Schedulers.computation()
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun io():Scheduler = Schedulers.io()
}