package com.egpaid.employeeapp.base.dataservice.remote

import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel
import io.reactivex.Single
import retrofit2.http.*

interface RetrofitService {
    @POST("auth/login")
    fun loginUser(@Body userData: LoginRequestModel): Single<LoginResponseSucessModel?>
}