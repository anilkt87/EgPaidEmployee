package com.egpaid.employeeapp.applock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel

abstract class AppLockViewModel : ViewModel() {
    abstract fun validatePin(digitValue: String)
    abstract fun validatePattern(password: String)
    abstract val pinStateLiveData: LiveData<BaseViewModel.State>

}