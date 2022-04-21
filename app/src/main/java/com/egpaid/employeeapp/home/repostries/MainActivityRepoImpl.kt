package com.egpaid.employeeapp.home.repostries

import com.egpaid.employeeapp.base.dataservice.remote.RetrofitService
import com.egpaid.employeeapp.home.view.entities.HomeModel
import io.reactivex.Single
import javax.inject.Inject

class MainActivityRepoImpl @Inject constructor(
    val api: RetrofitService) : MainActivityRepo {
    override fun getMyAppSideBar(token: String): Single<HomeModel?> {
        return  api.getMySideBar(token)
    }
}