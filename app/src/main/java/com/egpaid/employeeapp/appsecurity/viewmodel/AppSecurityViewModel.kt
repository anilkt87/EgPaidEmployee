package com.egpaid.employeeapp.appsecurity.viewmodel

import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel

abstract  class AppSecurityViewModel : BaseViewModel() {
    abstract val appSettingLiveData: LiveData<State>
    abstract fun appSecurityOption()
}