package com.egpaid.employeeapp.home.monitor.widget

import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.widget.ContentStateWidget
import com.egpaid.employeeapp.home.monitor.entities.Monitor

interface MonitorListIWidget : ContentStateWidget<HashMap<String, ArrayList<Monitor>>> {
    sealed class CallToAction {
        data class SpinnerClick(var value: String) : CallToAction()
    }
    val onClicked: LiveData<CallToAction>
}