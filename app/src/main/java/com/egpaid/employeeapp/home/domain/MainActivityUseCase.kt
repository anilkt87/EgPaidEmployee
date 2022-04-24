package com.egpaid.employeeapp.home.domain

import com.egpaid.employeeapp.home.view.entities.HomeModel
import com.egpaid.employeeapp.home.view.entities.myprofile.MyProfile
import io.reactivex.Single

interface MainActivityUseCase {

    fun execute(token:String)

    fun setCallback(callback: Callback)
    fun setMyProfileback(callback: MyProfileCallback)

    fun getMyProfile(token: String)

    interface Callback {
        fun onMySideBarSuccess(response:  List<HomeModel>)
        fun onMySideBarError(error: Throwable)
    }

    interface MyProfileCallback {
        fun onMyProfileSuccess(response:  MyProfile)
        fun onMyProfileError(error: Throwable)
    }

}