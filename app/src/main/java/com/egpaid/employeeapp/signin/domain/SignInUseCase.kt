package com.egpaid.employeeapp.signin.domain

import com.egpaid.employeeapp.base.base.domain.UseCase
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseErrorModel
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel

interface SignInUseCase : UseCase {
    fun execute(loginRequestModel: LoginRequestModel)
    fun setCallback(callback: Callback)

    interface Callback {
        fun onLoginSuccess(responseError: LoginResponseSucessModel?)
        fun onLoginError(error: Throwable)
    }
}