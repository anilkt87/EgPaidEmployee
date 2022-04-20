package com.egpaid.employeeapp.home.viewmodel

import android.app.usage.UsageStatsManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.home.appsusagemanager.AppsUsageManager
import com.egpaid.employeeapp.home.monitor.domain.MonitorUseCase
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly
import com.egpaid.employeeapp.home.view.entities.App
import java.util.*
import javax.inject.Inject

class HomeViewModelmpl @Inject constructor(
        private val monitorUseCase: MonitorUseCase,
        override val stateLiveData: MutableLiveData<State>,
        private val appsUsageManager: AppsUsageManager,
        private val appPreference: AppPreference
) : HomeViewModel(), MonitorUseCase.MonitorItemCallback, MonitorUseCase.WeeklyMonitorItemCallback {

    init {
        monitorUseCase.setMonitorItemCallBack(this)
        monitorUseCase.setWeeklyMonitorItemCallBack(this)
    }

    override fun onMonitorLoaded(data: List<Monitor>?) {

        stateLiveData.postValue(data?.let {
            appPreference.clearMonitorData()
            appPreference.saveLastMonitorData(it)
            State.SuccessListData(it)
        })
    }

    override fun onWeeklyMonitorLoaded(data: List<MonitorWeekly>?) {
        stateLiveData.postValue(data?.let {
            appPreference.clearWeeklyMonitorData()
            appPreference.saveLastWeeklyMonitorData(it)
            State.SuccessListData(it)
        })
    }

    override fun saveMonitorData(monitor: Monitor?) {
        monitorUseCase.saveMonitorData(monitor)
    }

    override fun saveWeeklyData(weekly: MonitorWeekly?) {
        monitorUseCase.saveMonitorWeeklyData(weekly)
    }

    override fun getMonitorData() {
        monitorUseCase.getMonitorData()
    }

    override fun getMonitorWeeklyData() {
        monitorUseCase.getMonitorWeeklyData()
    }

    override fun onMonitorLoadingError(throwable: Throwable) {
        stateLiveData.value = State.GeneralError
    }

    override fun getDailyAppUsage() {
        val beginTime = System.currentTimeMillis() - 1000 * 3600 * 24
        val endTime = System.currentTimeMillis()
        val listItem: ArrayList<App> = appsUsageManager.loadStatistics(
                beginTime, endTime,
                UsageStatsManager.INTERVAL_DAILY
        )
        Log.d("LISTSIZE", "Size-> " + listItem.size)
      //  appPreference.saveCategory(categoryMap)
        for (usageStats in listItem) {
            try {
                var lastTotal: Long = getLastTotalTime(usageStats.appName) ?: 0L
                var parentTime: Long = getParentTime(usageStats.appName) ?: 0L
                var childTime: Long = getChildTime(usageStats.appName) ?: 0L
                if (appPreference.isChildMode && usageStats.usageDuration > lastTotal) {
                    childTime += (usageStats.usageDuration - lastTotal)
                    saveMonitorData(
                            Monitor(
                                    usageStats.appName,
                                    usageStats.packageName,
                                    usageStats.category,
                                    usageStats.usageDuration,
                                    usageStats.usageDuration,
                                    parentTime,
                                    childTime
                            )
                    )

                } else if (!appPreference.isChildMode && usageStats.usageDuration > lastTotal) {
                    parentTime += (usageStats.usageDuration - lastTotal)
                    saveMonitorData(
                            Monitor(
                                    usageStats.appName,
                                    usageStats.packageName,
                                    usageStats.category,
                                    usageStats.usageDuration,
                                    usageStats.usageDuration,
                                    parentTime,
                                    childTime
                            )
                    )

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getWeeklyAppUsage() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val start = calendar.timeInMillis
        val end = System.currentTimeMillis()

        val listItem: ArrayList<App> = appsUsageManager.loadStatistics(
                start,
                end,
                UsageStatsManager.INTERVAL_WEEKLY
        )
        Log.d("LISTSIZE", "Size-> " + listItem.size)

        for (usageStats in listItem) {
            try {
                var lastTotal: Long = getWeeklyLastTotalTime(usageStats.appName) ?: 0L
                var parentTime: Long = getWeeklyParentTime(usageStats.appName) ?: 0L
                var childTime: Long = getWeeklyChildTime(usageStats.appName) ?: 0L
                if (appPreference.isChildMode && usageStats.usageDuration > lastTotal) {
                    childTime += (usageStats.usageDuration - lastTotal)
                    saveWeeklyData(
                            MonitorWeekly(
                                    usageStats.appName,
                                    usageStats.packageName,
                                    usageStats.category,
                                    usageStats.usageDuration,
                                    usageStats.usageDuration,
                                    parentTime,
                                    childTime
                            )
                    )

                } else if (!appPreference.isChildMode && usageStats.usageDuration > lastTotal) {
                    parentTime += (usageStats.usageDuration - lastTotal)
                    saveWeeklyData(
                            MonitorWeekly(
                                    usageStats.appName,
                                    usageStats.packageName,
                                    usageStats.category,
                                    usageStats.usageDuration,
                                    usageStats.usageDuration,
                                    parentTime,
                                    childTime
                            )
                    )

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun getParentTime(appName: String): Long? {
        try {

            appPreference.getLastMonitorData().forEach {
                if (it.appName == appName) {
                    return it.parentUsageDuration
                }
            }
        } catch (e: Exception) {
            return 0L
        }

        return 0L

    }

    private fun getChildTime(appName: String): Long? {
        try {
            appPreference.getLastMonitorData().forEach {
                if (it.appName == appName) {
                    return it.childUsageDuration
                }
            }
        } catch (e: Exception) {
            return 0L
        }

        return 0L

    }

    private fun getLastTotalTime(appName: String): Long? {
        try {
            appPreference.getLastMonitorData().forEach {
                if (it.appName == appName) {
                    return it.lastTotalTimeInForeground
                }
            }
        } catch (e: Exception) {
            0L
        }

        return 0L

    }

    //Weekly
    private fun getWeeklyParentTime(appName: String): Long? {
        try {

            appPreference.getLastMonitorData().forEach {
                if (it.appName == appName) {
                    return it.parentUsageDuration
                }
            }
        } catch (e: Exception) {
            return 0L
        }

        return 0L

    }

    private fun getWeeklyChildTime(appName: String): Long? {
        try {
            appPreference.getLastMonitorData().forEach {
                if (it.appName == appName) {
                    return it.childUsageDuration
                }
            }
        } catch (e: Exception) {
            return 0L
        }

        return 0L

    }

    private fun getWeeklyLastTotalTime(appName: String): Long? {
        try {
            appPreference.getLastMonitorData().forEach {
                if (it.appName == appName) {
                    return it.lastTotalTimeInForeground
                }
            }
        } catch (e: Exception) {
            0L
        }

        return 0L

    }


}