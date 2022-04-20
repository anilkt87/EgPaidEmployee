package com.egpaid.employeeapp.home.monitor.widget.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.adapter.AdapterDelegate
import com.egpaid.employeeapp.base.widget.DisplayableItem
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import javax.inject.Inject

class MonitorDailyDelegate @Inject constructor(
        private val layoutInflater: LayoutInflater,
        val context: Context,
) : AdapterDelegate<DisplayableItem>() {

    override fun isForViewType(items: MutableList<DisplayableItem>, position: Int) =
            items[position] is Monitor

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.view_monitor_daily_item, parent, false)
        return MonitoryDailyItemViewHolder(view, context)
    }

    override fun onBindViewHolder(
            items: MutableList<DisplayableItem>,
            position: Int,
            holder: RecyclerView.ViewHolder,
            payloads: MutableList<Any>
    ) {
        val viewHolder = holder as MonitoryDailyItemViewHolder
        viewHolder.bind(items[position] as Monitor)
    }
}