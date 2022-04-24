package com.egpaid.employeeapp.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.domain.MainActivityUseCase
import javax.inject.Inject

class MainActivityViewModelFactory @Inject constructor(
    private val stateLiveData: MutableLiveData<BaseViewModel.State>,
    private val appPreference: AppPreference,
    private val mainActivityUseCase: MainActivityUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return MainActivityViewModelImpl(
            stateLiveData,
            appPreference,
            mainActivityUseCase
        ) as T as T

    }


}