package com.egpaid.employeeapp.appsecurity.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import javax.inject.Inject

class AppSecurityViewModelImpl @Inject constructor(
    override val appSettingLiveData: MediatorLiveData<State>,
    val appPreference: AppPreference
) : AppSecurityViewModel() {

    override fun appSecurityOption() {
        appSettingLiveData.value = State.Success(appPreference.getAppSecurityOption())
    }
}