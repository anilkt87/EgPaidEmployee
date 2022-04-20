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
        if (apppPreference.getAppLockPin().isEmpty()) {
            pinStateLiveData.value = State.CREATE_PIN
        } else {
            pinStateLiveData.value = State.ENTER_PIN
        }
    }

    override fun saveAppLockPin(digitValue: String) {
        apppPreference.saveAppLockPin(digitValue)
        pinStateLiveData.value = State.ENTER_PIN
    }

    override fun validatePin(digitValue: String) {
        if (digitValue == apppPreference.getAppLockPin()) {
            pinStateLiveData.value = State.Success("")
        } else {
            pinStateLiveData.value = State.INCORRECT_PIN
        }
    }

    override fun changePassword(oldPassword: String, newpassword: String) {
        if (apppPreference.getAppLockPin() == oldPassword) {
            apppPreference.saveAppLockPin(newpassword)
            pinStateLiveData.value = State.ENTER_PIN
        } else {
            pinStateLiveData.value = State.INCORRECT_PIN
        }
    }
}