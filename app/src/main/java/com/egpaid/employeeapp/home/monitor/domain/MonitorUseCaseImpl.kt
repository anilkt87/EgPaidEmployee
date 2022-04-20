package com.egpaid.employeeapp.home.monitor.domain

import com.egpaid.employeeapp.base.base.schedulers.BaseSchedulerProvider
import com.egpaid.employeeapp.base.domain.BaseUseCase
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly
import com.egpaid.employeeapp.signin.local.MonitorDailyDao
import com.egpaid.employeeapp.signin.local.MonitorWeeklyDao
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MonitorUseCaseImpl @Inject constructor(
        compositeDisposable: CompositeDisposable,
        private val weeklyDao: MonitorWeeklyDao,
        private val monitorDailyDao: MonitorDailyDao,
        private val schedulerProvider: BaseSchedulerProvider
) : BaseUseCase(compositeDisposable), MonitorUseCase {




    override fun saveMonitorData(monitor: Monitor?) {
        monitor?.let {
            trackDisposable(Completable.fromCallable { monitorDailyDao.insertMonitorItem(it) }
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.computation())
                    .subscribe({}, {}))
        }
    }

    override fun saveMonitorWeeklyData(monitorWeekly: MonitorWeekly?) {
        monitorWeekly?.let {
            trackDisposable(Completable.fromCallable { weeklyDao.insertWeeklyMonitorItem(it) }
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.computation())
                    .subscribe({}, {}))
        }
    }


    private var monitorItemCallBack: MonitorUseCase.MonitorItemCallback? = null

    private var callback: MonitorUseCase.WeeklyMonitorItemCallback? = null
    override fun setMonitorItemCallBack(monitorItemCallBack: MonitorUseCase.MonitorItemCallback) {
        this.monitorItemCallBack = monitorItemCallBack
    }

    override fun setWeeklyMonitorItemCallBack(callback: MonitorUseCase.WeeklyMonitorItemCallback) {
        this.callback = callback
    }

    override fun getMonitorData() {
        trackDisposable(Single.fromCallable { monitorDailyDao.getMonitorItem() }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.computation())
                .subscribe(::onMonitorSuccess, ::onMonitorItemError))
    }


    override fun getMonitorWeeklyData() {
        trackDisposable(Single.fromCallable { weeklyDao.getMonitorWeeklyItem() }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.computation())
                .subscribe(::onWeeklyMonitorSuccess, ::onMonitorItemError))
    }



    private fun onWeeklyMonitorSuccess(data: List<MonitorWeekly>) {

        callback?.onWeeklyMonitorLoaded(data = data)
    }

    private fun onMonitorSuccess(data: List<Monitor>) {
        monitorItemCallBack?.onMonitorLoaded(data)
    }


    private fun onMonitorItemError(throwable: Throwable) {
        monitorItemCallBack?.onMonitorLoadingError(throwable)
    }


    override fun cleanup() {
        super.cleanup()
        monitorItemCallBack = null
        callback = null
    }


}