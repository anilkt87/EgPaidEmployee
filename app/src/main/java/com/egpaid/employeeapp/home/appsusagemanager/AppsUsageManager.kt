package com.egpaid.employeeapp.home.appsusagemanager

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.egpaid.employeeapp.home.monitor.entities.Category
import com.egpaid.employeeapp.home.view.entities.App
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

class AppsUsageManager(private val context: Context) {

    fun loadStatistics(beginTime: Long, entTime: Long, usageManager: Int): ArrayList<App> {

        val usm =
                context.getSystemService(AppCompatActivity.USAGE_STATS_SERVICE) as UsageStatsManager

        //        List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, System.currentTimeMillis() - 1000 * 3600 * 24, System.currentTimeMillis());

        var appList = usm.queryUsageStats(
                usageManager,
                beginTime,
                entTime
        )
        appList = appList.stream().filter { app: UsageStats -> app.totalTimeInForeground > 0 }
                .collect(Collectors.toList())
        return if (appList.size > 0) {
            val mySortedMap: MutableMap<String, UsageStats> = TreeMap()
            for (usageStats in appList) {
                mySortedMap[usageStats.packageName] = usageStats
            }
            showAppsUsage(mySortedMap)
        } else {
            emptyList<App>() as ArrayList<App>
        }
    }


    private fun showAppsUsage(mySortedMap: MutableMap<String, UsageStats>): ArrayList<App> {

        val jsonFileString = getJsonDataFromAsset(context, "app_category.json")

        val gson = Gson()
        val listCategoryType = object : TypeToken<List<Category>>() {}.type

        var category: List<Category> = gson.fromJson(jsonFileString, listCategoryType)


        //public void showAppsUsage(List<UsageStats> usageStatsList) {
        val appsList = ArrayList<App>()
        val usageStatsList: List<UsageStats> = ArrayList(mySortedMap.values)
        Collections.sort(usageStatsList) { z1: UsageStats, z2: UsageStats ->
            z1.totalTimeInForeground.compareTo(z2.totalTimeInForeground)
        }
        for (usageStats in usageStatsList) {
            try {

                val packageName = usageStats.packageName
                val packageNames = packageName.split("\\.").toTypedArray()

                var appName = packageNames[packageNames.size - 1].trim { it <= ' ' }
                if (isAppInfoAvailable(usageStats)) {
                    val ai = context.packageManager.getApplicationInfo(packageName, 0)
                    appName = context.packageManager.getApplicationLabel(ai).toString()
                }

                appsList.add(
                        App(
                                packageName,
                                getType(category, packageName), appName,

                                usageStats.totalTimeInForeground
                        )
                )

            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
        }

        return appsList

    }

    private fun isAppInfoAvailable(usageStats: UsageStats): Boolean {
        return try {
            context.packageManager.getApplicationInfo(usageStats.packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun getType(list: List<Category>, packageName: String): String {
        list.forEach {
            if (it.packageName == packageName) {
                return it.type
            }
        }
        return "Other"
    }


}