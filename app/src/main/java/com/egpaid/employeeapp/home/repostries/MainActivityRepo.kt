package com.egpaid.employeeapp.home.repostries

import com.egpaid.employeeapp.home.view.entities.HomeModel
import io.reactivex.Single

interface MainActivityRepo {
    fun getMyAppSideBar(token: String): Single<HomeModel?>
}