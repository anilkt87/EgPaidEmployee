package com.egpaid.employeeapp.home.monitor.entities

import androidx.room.TypeConverter
import com.google.gson.Gson

class MonitorWeeklyConverter {
    @TypeConverter
    fun listToJsonString(value: List<MonitorWeekly>?): String = Gson().toJson(value)
}