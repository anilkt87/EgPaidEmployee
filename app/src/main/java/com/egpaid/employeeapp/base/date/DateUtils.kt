package com.egpaid.employeeapp.base.date

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class DateUtils {

    companion object {

        fun getTodayDate(): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                current.format(formatter)
            } else {
                var date = Date()
                val formatter = SimpleDateFormat("MMM dd yyyy")
                formatter.format(date)
            }
        }


    }

}