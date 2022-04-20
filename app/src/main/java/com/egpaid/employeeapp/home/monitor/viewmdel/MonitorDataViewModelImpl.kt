package com.egpaid.employeeapp.home.monitor.viewmdel

import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.home.monitor.domain.MonitorUseCase
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly

class MonitorDataViewModelImpl(
        private val monitorUseCase: MonitorUseCase,
        override val stateLiveData: MutableLiveData<State>,
        private val appPreference: AppPreference
) : MonitorDataViewModel(), MonitorUseCase.MonitorItemCallback,
        MonitorUseCase.WeeklyMonitorItemCallback {

    init {
        monitorUseCase.setMonitorItemCallBack(this)
        monitorUseCase.setWeeklyMonitorItemCallBack(this)
    }

    override fun getMonitorData() {
        monitorUseCase.getMonitorData()
    }

    override fun getWeeklyMonitorData() {
        monitorUseCase.getMonitorWeeklyData()
    }

    override fun onMonitorLoaded(data: List<Monitor>?) {
        var mapOfData = hashMapOf<String, ArrayList<Monitor>>()
        data?.forEach {
            if (it.category !in mapOfData) {
                var listOfMonitor: ArrayList<Monitor> = ArrayList()
                listOfMonitor.add(it)
                mapOfData[it.category] = listOfMonitor

            } else {
                var listOfMonitor: ArrayList<Monitor> = ArrayList()
                val getItem = mapOfData[it.category]
                getItem?.add(it)
                getItem?.let { it1 -> listOfMonitor.addAll(it1) }
                mapOfData[it.category] = listOfMonitor
            }
        }
        stateLiveData.postValue(data?.let { State.SuccessDailyCategoryData(mapOfData) })
    }

    override fun onMonitorLoadingError(throwable: Throwable) {
        stateLiveData.value = State.GeneralError
    }

    override fun onWeeklyMonitorLoaded(data: List<MonitorWeekly>?) {
        var mapOfData = hashMapOf<String, ArrayList<Monitor>>()
        data?.forEach {
            if (it.category !in mapOfData) {
                var listOfMonitor: ArrayList<Monitor> = ArrayList()

                listOfMonitor.add(
                        Monitor(
                                it.appName,
                                it.packageName,
                                it.category,
                                it.totalAppTimeInForeground,
                                it.lastTotalTimeInForeground,
                                it.childUsageDuration,
                                it.parentUsageDuration
                        )
                )
                mapOfData[it.category] = listOfMonitor

            } else {
                var listOfMonitor: ArrayList<Monitor> = ArrayList()
                val getItem = mapOfData[it.category]
                getItem?.add(
                        Monitor(
                                it.appName,
                                it.packageName,
                                it.category,
                                it.totalAppTimeInForeground,
                                it.lastTotalTimeInForeground,
                                it.childUsageDuration,
                                it.parentUsageDuration
                        )
                )
                getItem?.let { it1 -> listOfMonitor.addAll(it1) }
                mapOfData[it.category] = listOfMonitor
            }
        }
        stateLiveData.postValue(data?.let { State.SuccessDailyCategoryData(mapOfData) })
    }


}

