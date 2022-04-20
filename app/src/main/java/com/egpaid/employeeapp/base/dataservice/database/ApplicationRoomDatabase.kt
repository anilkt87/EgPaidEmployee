package com.egpaid.employeeapp.base.dataservice.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.egpaid.employeeapp.base.database.DbConstants
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.MonitorConverter
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeeklyConverter
import com.egpaid.employeeapp.signin.local.MonitorDailyDao
import com.egpaid.employeeapp.signin.local.MonitorWeeklyDao

@Database(
        entities = [Monitor::class, MonitorWeekly::class],
        version = DbConstants.PERSON_DB_VERSION
)
@TypeConverters( MonitorConverter::class, MonitorWeeklyConverter::class)
abstract class ApplicationRoomDatabase : RoomDatabase() {
    abstract fun monitorDao(): MonitorDailyDao
    abstract fun monitorWeeklyDao(): MonitorWeeklyDao

}