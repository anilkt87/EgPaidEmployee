package com.egpaid.employeeapp.home.viewmodel

import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.MonitorWeekly

abstract class MainActivityViewModel : BaseViewModel() {

    abstract val stateLiveData: LiveData<State>
    abstract fun getMyAppSideBar()

}