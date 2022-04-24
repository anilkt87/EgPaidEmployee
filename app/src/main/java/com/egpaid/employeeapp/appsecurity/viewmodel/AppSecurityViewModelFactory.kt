package com.egpaid.employeeapp.appsecurity.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import javax.inject.Inject

class AppSecurityViewModelFactory @Inject constructor(
    private val signInLiveData: MediatorLiveData<BaseViewModel.State>,
    val appPreference: AppPreference
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return AppSecurityViewModelImpl(signInLiveData,appPreference) as T as T

    }
}