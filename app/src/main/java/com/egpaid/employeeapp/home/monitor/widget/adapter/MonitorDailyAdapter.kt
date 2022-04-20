package com.egpaid.employeeapp.home.monitor.widget.adapter

import com.egpaid.employeeapp.base.adapter.DiffCallback
import com.egpaid.employeeapp.base.adapter.DisplayableAdapter
import com.egpaid.employeeapp.home.monitor.entities.Monitor

abstract  class MonitorDailyAdapter(diffCallback: DiffCallback) : DisplayableAdapter(diffCallback) {
    abstract fun setMonitorItem(item: List<Monitor>)
}