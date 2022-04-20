package com.egpaid.employeeapp.home.monitor.widget.adapter

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.iamkamrul.expandablerecyclerviewlist.viewholder.ChildViewHolder
import java.util.concurrent.TimeUnit

class CategoryListViewHolder(view: View, val context: Context) : ChildViewHolder(view) {
    fun bind(item: Monitor) {
        itemView.findViewById<TextView>(R.id.tv_app_name).text = item.appName
        appIcon(item.packageName, itemView.findViewById(R.id.img_app))

        val parentDuration = item.parentUsageDuration ?: 0L
        val childDuration = item.childUsageDuration ?: 0L

        itemView.findViewById<TextView>(R.id.tv_parent_time).text =
                getDurationBreakdown(parentDuration)
        itemView.findViewById<TextView>(R.id.tv_child_time).text =
                getDurationBreakdown(childDuration)
        itemView.findViewById<TextView>(R.id.tv_total_time).text =
                getDurationBreakdown(childDuration + parentDuration)

        if (parentDuration > 0) {
            val parentUsagePercentage = (parentDuration * 100 / (childDuration + parentDuration))
            itemView.findViewById<ProgressBar>(R.id.tv_parent_progress).progress =
                    parentUsagePercentage.toInt()
        }
        if (childDuration > 0) {
            val childUsagePercentage = (childDuration * 100 / (childDuration + parentDuration))
            itemView.findViewById<ProgressBar>(R.id.tv_child_progress).progress =
                    childUsagePercentage.toInt()
        }

    }

    fun appIcon(packageName: String, imagerView: ImageView) {
        try {
            val icon: Drawable = context.packageManager
                    .getApplicationIcon(packageName)

            imagerView.setImageDrawable(icon);

        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun getDurationBreakdown(millis: Long): String? {
        var millis = millis
        require(millis >= 0) { "Duration must be greater than zero!" }
        val hours = TimeUnit.MILLISECONDS.toHours(millis)
        millis -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
        millis -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis)
        return "$hours h $minutes m $seconds s"
    }
}