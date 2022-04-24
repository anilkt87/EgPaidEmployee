package com.egpaid.employeeapp.applock.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel.*
import javax.inject.Inject

class AppLockViewModelmpl @Inject constructor(
    override val pinStateLiveData: MediatorLiveData<State>,
    private val apppPreference: AppPreference
) : AppLockViewModel() {

    init {
        when {
            apppPreference.getAppSecurityOption() == 1 -> {
                pinStateLiveData.value = State.PinOption
            }
            apppPreference.getAppSecurityOption() == 2 -> {
                pinStateLiveData.value = State.PatternOption
            }
            apppPreference.getAppSecurityOption() == 3 -> {
                pinStateLiveData.value = State.BiometricOption
            }
        }
    }

    override fun validatePin(digitValue: String) {
        if (digitValue == apppPreference.getAppLockPin()) {
            pinStateLiveData.value = State.Success("")
        } else {
            pinStateLiveData.value = State.INCORRECT_PIN
        }
    }


}