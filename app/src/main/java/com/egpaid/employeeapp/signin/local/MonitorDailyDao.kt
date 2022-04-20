package com.egpaid.employeeapp.signin.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.egpaid.employeeapp.home.monitor.entities.Monitor

@Dao
interface MonitorDailyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMonitorItem(monitor: Monitor): Long

    @Query("SELECT * FROM Monitor")
    fun getMonitorItem(): List<Monitor>

    @Query("SELECT * FROM Monitor WHERE appName=:appName")
    fun getMonitorAllData(appName: String): Monitor

}