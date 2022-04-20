package com.egpaid.employeeapp.signin.repository

import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseErrorModel
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel
import io.reactivex.Single
interface SignInRepo {


    fun login(loginRequestModel: LoginRequestModel): Single<LoginResponseSucessModel?>

}