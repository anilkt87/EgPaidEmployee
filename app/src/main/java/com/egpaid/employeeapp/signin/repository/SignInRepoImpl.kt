package com.egpaid.employeeapp.signin.repository

import com.egpaid.employeeapp.base.domain.ResultData
import com.egpaid.employeeapp.base.base.schedulers.BaseSchedulerProvider
import com.egpaid.employeeapp.base.base.schedulers.TrampolineSchedulerProvider
import com.egpaid.employeeapp.base.domain.ResponseServiceCode
import com.egpaid.employeeapp.base.domain.model.ErrorModel
import com.egpaid.employeeapp.signin.local.PersonDao
import com.egpaid.employeeapp.signin.entities.Person
import com.egpaid.employeeapp.base.dataservice.remote.RetrofitService
import com.egpaid.employeeapp.base.domain.ResultData.Companion.fromData
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseModel
import com.egpaid.employeeapp.utils.Constant
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import retrofit2.HttpException
import javax.inject.Inject


class SignInRepoImpl @Inject constructor(
    val api: RetrofitService,
) : SignInRepo {
    override fun login(loginRequestModel: LoginRequestModel): Single<LoginResponseModel> {
        return api.loginUser(loginRequestModel)
    }
}