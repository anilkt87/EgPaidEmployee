package com.egpaid.employeeapp.home.monitor.viewmdel

import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference

class MonitorDataViewModelImpl(
    override val stateLiveData: MutableLiveData<State>,
    private val appPreference: AppPreference
) : MonitorDataViewModel()



