package com.egpaid.employeeapp.home.monitor.entities

import androidx.room.TypeConverter
import com.google.gson.Gson

class MonitorConverter {

    @TypeConverter
    fun listToJsonString(value: List<Monitor>?): String = Gson().toJson(value)

}