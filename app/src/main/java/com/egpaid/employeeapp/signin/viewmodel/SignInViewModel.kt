package com.egpaid.employeeapp.signin.viewmodel

import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.signin.entities.LoginRequestModel


abstract class SignInViewModel :BaseViewModel() {
    abstract val signInLiveData: LiveData<State>
    abstract fun getLoginData(loginRequestModel: LoginRequestModel)
}