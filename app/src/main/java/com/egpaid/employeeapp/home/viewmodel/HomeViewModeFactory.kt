package com.egpaid.employeeapp.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.appsusagemanager.AppsUsageManager
import com.egpaid.employeeapp.home.monitor.domain.MonitorUseCase
import javax.inject.Inject

class HomeViewModeFactory @Inject constructor(
        private val monitorUseCase: MonitorUseCase,
        private val stateLiveData: MutableLiveData<BaseViewModel.State>,
        private val appsUsageManager: AppsUsageManager,
        private val appPreference: AppPreference
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return HomeViewModelmpl(
                monitorUseCase,
                stateLiveData,
                appsUsageManager,
                appPreference
        ) as T as T

    }


}