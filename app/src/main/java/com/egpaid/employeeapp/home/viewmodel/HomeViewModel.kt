package com.egpaid.employeeapp.home.viewmodel

import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly

abstract class HomeViewModel : BaseViewModel() {

    abstract val stateLiveData: LiveData<State>
    abstract fun saveMonitorData(monitor: Monitor?)
    abstract fun saveWeeklyData(weekly: MonitorWeekly?)
    abstract fun getMonitorData()
    abstract fun getMonitorWeeklyData()
    abstract fun getDailyAppUsage()
    abstract fun getWeeklyAppUsage()

}