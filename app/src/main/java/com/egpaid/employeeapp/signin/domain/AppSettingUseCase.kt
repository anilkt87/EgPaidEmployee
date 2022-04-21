package com.egpaid.employeeapp.signin.domain

import com.egpaid.employeeapp.base.base.domain.UseCase
import com.egpaid.employeeapp.signin.entities.AppSettingResponse

interface AppSettingUseCase : UseCase {
    fun execute(token:String)
    fun setCallback(callback: Callback)

    interface Callback {
        fun onAppSettingSuccess(responseError: AppSettingResponse?)
        fun onAppSettingError(error: Throwable)
    }
}