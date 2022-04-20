package com.egpaid.employeeapp.home.monitor.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.egpaid.employeeapp.base.widget.DisplayableItem
import org.jetbrains.annotations.NonNls

@Entity
data class MonitorWeekly(
        @PrimaryKey
        @NonNls
        var appName: String,
        var packageName: String,
        var category:String,
        var totalAppTimeInForeground: Long? = 0L,
        var lastTotalTimeInForeground: Long? = 0L,
        var parentUsageDuration: Long? = 0L,
        var childUsageDuration: Long? = 0L
) : DisplayableItem
