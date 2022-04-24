package com.egpaid.employeeapp.home.repostries

import com.egpaid.employeeapp.home.view.entities.HomeModel
import com.egpaid.employeeapp.home.view.entities.myprofile.MyProfile
import io.reactivex.Single

interface MainActivityRepo {
    fun getMyAppSideBar(token: String): Single<List<HomeModel>>
    fun getMyProfile(token: String): Single<MyProfile>
}