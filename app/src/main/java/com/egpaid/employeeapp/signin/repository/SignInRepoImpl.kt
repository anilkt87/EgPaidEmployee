package com.egpaid.employeeapp.signin.repository

import com.egpaid.employeeapp.base.dataservice.remote.RetrofitService
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseErrorModel
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel
import io.reactivex.Single
import javax.inject.Inject


class SignInRepoImpl @Inject constructor(
    val api: RetrofitService) : SignInRepo {
    override fun login(loginRequestModel: LoginRequestModel): Single<LoginResponseSucessModel?> {
        return api.loginUser(loginRequestModel)
    }
}