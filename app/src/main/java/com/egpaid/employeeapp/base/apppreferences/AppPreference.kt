package com.egpaid.employeeapp.base.apppreferences

import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly
import com.egpaid.employeeapp.home.view.entities.HomeModel
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel

interface AppPreference {
    var userName: String
    fun getWebsite(): String
    fun setWebsite(website: String)

    fun saveAppLockPin(pin: String)
    fun getAppLockPin(): String

    var isChildMode: Boolean

    fun saveLastMonitorData(data: List<Monitor>)

    fun getLastMonitorData(): List<Monitor>

    fun clearMonitorData()


    fun saveLastWeeklyMonitorData(data: List<MonitorWeekly>)

    fun getLastWeeklyMonitorData(): List<MonitorWeekly>

    fun clearWeeklyMonitorData()

    fun saveCategory(data: MutableMap<String, String>)
    fun getCategory(): HashMap<String, String>

    fun saveUserData(userData: LoginResponseSucessModel?)
    fun getUserData(): LoginResponseSucessModel?

    fun saveMySideBarData(home: List<HomeModel>)
    fun getGetSideBarData(): List<HomeModel>
}