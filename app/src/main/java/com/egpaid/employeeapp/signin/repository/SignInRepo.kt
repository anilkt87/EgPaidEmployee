package com.egpaid.employeeapp.signin.repository

import com.egpaid.employeeapp.base.domain.ResultData
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseModel
import com.egpaid.employeeapp.signin.entities.Person
import io.reactivex.Observable
import io.reactivex.Single
interface SignInRepo {


    fun login(loginRequestModel: LoginRequestModel): Single<LoginResponseModel>

}