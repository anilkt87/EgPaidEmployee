package com.egpaid.employeeapp.home.domain

import com.egpaid.employeeapp.home.view.entities.HomeModel

interface MainActivityUseCase {

    fun execute(token:String)
    fun setCallback(callback: Callback)

    interface Callback {
        fun onMySideBarSuccess(responseError: HomeModel?)
        fun onMySideBarError(error: Throwable)
    }

}