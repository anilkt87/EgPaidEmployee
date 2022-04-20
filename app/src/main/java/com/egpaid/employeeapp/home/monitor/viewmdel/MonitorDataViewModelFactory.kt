package com.egpaid.employeeapp.home.monitor.viewmdel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.monitor.domain.MonitorUseCase
import javax.inject.Inject

class MonitorDataViewModelFactory @Inject constructor(
        private val monitorUseCase: MonitorUseCase,
        private val stateLiveData: MutableLiveData<BaseViewModel.State>,
        private val appPreference: AppPreference
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return MonitorDataViewModelImpl(monitorUseCase, stateLiveData, appPreference) as T as T

    }


}