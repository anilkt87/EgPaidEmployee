package com.egpaid.employeeapp.home.monitor.domain

import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly

interface MonitorUseCase {

    fun saveMonitorData(monitor: Monitor?)

    fun saveMonitorWeeklyData(monitorWeekly: MonitorWeekly?)

    //  GetListOfMonitorItem
    interface MonitorItemCallback {
        fun onMonitorLoaded(data: List<Monitor>?)
        fun onMonitorLoadingError(throwable: Throwable)
    }

    fun setMonitorItemCallBack(callback: MonitorItemCallback)

    fun getMonitorData()

    // Weekgly

    interface WeeklyMonitorItemCallback {
        fun onWeeklyMonitorLoaded(data: List<MonitorWeekly>?)
    }

    fun setWeeklyMonitorItemCallBack(callback: WeeklyMonitorItemCallback)

    fun getMonitorWeeklyData()


}