package com.egpaid.employeeapp.base.apppreferences

import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly
import com.egpaid.employeeapp.home.view.entities.HomeModel
import com.egpaid.employeeapp.home.view.entities.myprofile.MyProfile
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel

interface AppPreference {
    fun saveAppLockPin(pin: String)
    fun getAppLockPin(): String

    fun savePatternLockData(pin: String)
    fun getGetPatternLockData(): String

    fun saveUserData(userData: LoginResponseSucessModel?)
    fun getUserData(): LoginResponseSucessModel?

    fun saveMySideBarData(home: List<HomeModel>)
    fun getGetSideBarData(): List<HomeModel>

    fun saveMyProfileData(myProfile: MyProfile)
    fun getMyProfileData(): MyProfile

    fun setAppSecurityOption(position: Int)
    fun getAppSecurityOption(): Int
}