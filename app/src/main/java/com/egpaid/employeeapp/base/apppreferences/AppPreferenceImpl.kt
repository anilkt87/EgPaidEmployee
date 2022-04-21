package com.egpaid.employeeapp.base.apppreferences

import android.content.Context
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class AppPreferenceImpl @Inject constructor(context: Context) : AppPreference {

    companion object {
        const val USER_NAME = "user_name"
        const val WEBSITE = "website"
        const val APP_LOCK_PIN = "app_lock_pin"
        const val IS_CHILD_MODE = "is_child_mode"
        const val LAST_MONITOR_DATA = "last_monitor_data"
        const val LAST_MONITOR_WEEKLY_DATA = "last_monitor_weekly"
        const val APP_CATEGORY = "app_category"
        const val USER_RESPONSE_DATA ="user_response_data"
    }

    private var preference = context.getSharedPreferences("dagger-pref", Context.MODE_PRIVATE)
    private var editor = preference.edit()

    override var userName: String
        get() = getString(USER_NAME)
        set(value) {
            saveString(USER_NAME, value)
        }


    override fun getWebsite(): String {
        return getString(WEBSITE)
    }

    override fun setWebsite(website: String) {
        saveString(WEBSITE, website)
    }

    override fun saveAppLockPin(pin: String) {
        editor.putString(APP_LOCK_PIN, pin).apply()
    }

    override fun getAppLockPin(): String {
        return preference.getString(APP_LOCK_PIN, "") ?: ""
    }

    override var isChildMode: Boolean
        get() = getBoolean(IS_CHILD_MODE)
        set(value) {
            saveBoolean(IS_CHILD_MODE, value)
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


    override fun saveLastMonitorData(data: List<Monitor>) {
        val gson = Gson()
        val json = gson.toJson(data)
        editor.putString(LAST_MONITOR_DATA, json).apply()
    }

    override fun getLastMonitorData(): List<Monitor> {

        val gson = Gson()
        val json = preference.getString(LAST_MONITOR_DATA, "")
        val type = object : TypeToken<List<Monitor?>?>() {}.type
        return gson.fromJson(json, type)
    }

    override fun clearMonitorData() {
        editor.remove(LAST_MONITOR_DATA).apply()

    }

    override fun saveLastWeeklyMonitorData(data: List<MonitorWeekly>) {
        val gson = Gson()
        val json = gson.toJson(data)
        editor.putString(LAST_MONITOR_WEEKLY_DATA, json).apply()
    }

    override fun getLastWeeklyMonitorData(): List<MonitorWeekly> {
        val gson = Gson()
        val json = preference.getString(LAST_MONITOR_WEEKLY_DATA, "")
        val type = object : TypeToken<List<MonitorWeekly?>?>() {}.type
        return gson.fromJson(json, type)
    }

    override fun clearWeeklyMonitorData() {
        editor.remove(LAST_MONITOR_WEEKLY_DATA).apply()
    }

    override fun saveCategory(data: MutableMap<String, String>) {
        val gson = Gson()
        val json = gson.toJson(data)
        val jsonString: String = json.toString()
        editor.putString(APP_CATEGORY, jsonString).apply()
    }

    override fun getCategory(): HashMap<String, String> {
        var data = hashMapOf<String,String>()
        val gson = Gson()
        val json = preference.getString(APP_CATEGORY, "")
        val type = object : TypeToken<HashMap<String, String>>(){}.type
        data = gson.fromJson(json, type)
        return data
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


}