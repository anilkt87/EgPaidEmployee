package com.egpaid.employeeapp.base.apppreferences

import android.content.Context
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly
import com.egpaid.employeeapp.home.view.entities.HomeModel
import com.egpaid.employeeapp.home.view.entities.myprofile.MyProfile
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class AppPreferenceImpl @Inject constructor(context: Context) : AppPreference {

    companion object {
        const val USER_NAME = "user_name"
        const val WEBSITE = "website"
        const val APP_LOCK_PIN = "app_lock_pin"
        const val APP_PATTERN_LOCK_PIN = "app_pattern_lock"
        const val IS_CHILD_MODE = "is_child_mode"
        const val LAST_MONITOR_DATA = "last_monitor_data"
        const val LAST_MONITOR_WEEKLY_DATA = "last_monitor_weekly"
        const val APP_CATEGORY = "app_category"
        const val USER_RESPONSE_DATA = "user_response_data"
        const val MY_SIDE_BAR_DATA = "mysidebardata"
        const val USER_PROFILE_DATA = "myprofileData"
        const val APP_SECURTIY_OPTION = "app_security_option"
    }

    private var preference = context.getSharedPreferences("dagger-pref", Context.MODE_PRIVATE)
    private var editor = preference.edit()


    override fun saveAppLockPin(pin: String) {
        editor.putString(APP_LOCK_PIN, pin).apply()
    }

    override fun getAppLockPin(): String {
        return preference.getString(APP_LOCK_PIN, "") ?: ""
    }

    override fun savePatternLockData(pin: String) {
        editor.putString(APP_PATTERN_LOCK_PIN, pin).apply()
    }

    override fun getGetPatternLockData(): String {
        return preference.getString(APP_PATTERN_LOCK_PIN, "") ?: ""
    }


    private fun saveString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    private fun getString(key: String, defaultValue: String = ""): String {
        return preference.getString(key, defaultValue) ?: defaultValue
    }

    private fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    private fun getBoolean(key: String): Boolean {
        return preference.getBoolean(key, false) ?: false
    }

    override fun saveUserData(userData: LoginResponseSucessModel?) {
        val gson = Gson()
        val json = gson.toJson(userData)
        val jsonString: String = json.toString()
        editor.putString(USER_RESPONSE_DATA, jsonString).apply()
    }

    override fun getUserData(): LoginResponseSucessModel? {
        val gson = Gson()
        val json = preference.getString(USER_RESPONSE_DATA, "")
        val type = object : TypeToken<LoginResponseSucessModel>() {}.type
        return gson.fromJson(json, type)
    }

    override fun saveMySideBarData(home: List<HomeModel>) {
        val gson = Gson()
        val json = gson.toJson(home)
        editor.putString(MY_SIDE_BAR_DATA, json).apply()
    }

    override fun getGetSideBarData(): List<HomeModel> {
        val gson = Gson()
        val json = preference.getString(MY_SIDE_BAR_DATA, "")
        val type = object : TypeToken<List<HomeModel?>?>() {}.type
        return gson.fromJson(json, type)
    }

    override fun saveMyProfileData(myProfile: MyProfile) {
        val gson = Gson()
        val json = gson.toJson(myProfile)
        val jsonString: String = json.toString()
        editor.putString(USER_PROFILE_DATA, jsonString).apply()
    }

    override fun getMyProfileData(): MyProfile {
        val gson = Gson()
        val json = preference.getString(USER_PROFILE_DATA, "")
        val type = object : TypeToken<MyProfile>() {}.type
        return gson.fromJson(json, type)
    }

    override fun setAppSecurityOption(position: Int) {
        editor.putInt(APP_SECURTIY_OPTION, position).apply()
    }

    override fun getAppSecurityOption(): Int {
        return preference.getInt(APP_SECURTIY_OPTION, 0) ?: 0
    }


}