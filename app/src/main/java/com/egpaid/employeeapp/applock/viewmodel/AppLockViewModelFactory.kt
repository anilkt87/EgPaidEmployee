package com.egpaid.employeeapp.applock.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import javax.inject.Inject

class AppLockViewModelFactory @Inject constructor(
    private val pinStateLiveData: MediatorLiveData<BaseViewModel.State>,
    private  val apppPreference: AppPreference
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return AppLockViewModelmpl(pinStateLiveData, apppPreference) as T as T
    }
}