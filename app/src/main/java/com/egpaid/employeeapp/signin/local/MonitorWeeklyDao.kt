package com.egpaid.employeeapp.signin.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly

@Dao
interface  MonitorWeeklyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeeklyMonitorItem(monitor: MonitorWeekly): Long

    @Query("SELECT * FROM MonitorWeekly")
    fun getMonitorWeeklyItem(): List<MonitorWeekly>
}