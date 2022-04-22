package com.egpaid.employeeapp.home.homedashboard.drawberviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import javax.inject.Inject

class DrawableViewModelFactory @Inject constructor(
    private val stateLiveData: MutableLiveData<BaseViewModel.State>,
    private val appPreference: AppPreference
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return DrawableViewModelImpl( stateLiveData, appPreference) as T as T

    }


}