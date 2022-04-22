package com.egpaid.employeeapp.home.domain

import com.egpaid.employeeapp.home.view.entities.HomeModel
import io.reactivex.Single

interface MainActivityUseCase {

    fun execute(token:String)
    fun setCallback(callback: Callback)

    interface Callback {
        fun onMySideBarSuccess(response:  List<HomeModel>)
        fun onMySideBarError(error: Throwable)
    }

}