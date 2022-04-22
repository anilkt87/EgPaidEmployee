package com.egpaid.employeeapp.base.dataservice.remote

import com.egpaid.employeeapp.home.view.entities.HomeModel
import com.egpaid.employeeapp.signin.entities.AppSettingResponse
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitService {
    @POST("auth/login")
    fun loginUser(@Body userData: LoginRequestModel): Single<LoginResponseSucessModel?>

    @GET("crm/appsetting")
    fun getAppSetting(@Header("Authorization") token: String): Single<AppSettingResponse?>

    @GET("crm/mysidebar")
    fun getMySideBar(@Header("Authorization") token: String): Single<List<HomeModel>>
}