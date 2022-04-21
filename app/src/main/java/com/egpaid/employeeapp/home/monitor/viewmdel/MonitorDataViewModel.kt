package com.egpaid.employeeapp.home.monitor.viewmdel

import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel

abstract class MonitorDataViewModel :BaseViewModel() {

    abstract val stateLiveData: LiveData<State>

}