package com.egpaid.employeeapp.home.monitor.widget.adapter

import com.egpaid.employeeapp.base.adapter.DiffCallback
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import javax.inject.Inject
import javax.inject.Named

class MonitorDailyAdapterImpl @Inject constructor(
        @Named("ForMonitorDailyAdapter")
        diffCallback: DiffCallback,
        monitorDailyDelegate: MonitorDailyDelegate
) : MonitorDailyAdapter(diffCallback) {

    init {
        getDelegatesManager().addDelegate(monitorDailyDelegate)
    }

    override fun setMonitorItem(item: List<Monitor>) {
        setItems(item)
    }


}