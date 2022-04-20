package com.egpaid.employeeapp.base.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.signin.entities.LoginResponseErrorModel
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    sealed class State {
        data class Success(val data: Any) : State()
        class SuccessListData(val response: List<Any>) : State()
        class SuccessDailyCategoryData(val data: HashMap<String, ArrayList<Monitor>>) : State()
        class Error(error: Throwable) : State()
        object EmptyData : State()
        object Loading : State()
        object NetworkError : State()
        object GeneralError : State()

        object ENTER_PIN : State()
        object CHANGE_PIN : State()
        object CREATE_PIN : State()
        object INCORRECT_PIN : State()
        data class LoginSuccess(val loginResponseSucessModel: LoginResponseSucessModel) : State()
        data class LoginError(val loginResponseErrorModel: LoginResponseErrorModel) : State()
    }


}