package com.egpaid.employeeapp.signin.mapper

import com.egpaid.employeeapp.base.basemapper.Mapper
import com.egpaid.employeeapp.signin.entities.LoginResponseModel
import javax.inject.Inject

class LoginMapper @Inject constructor() : Mapper<Map<LoginResponseModel, String>, LoginResponseModel> {
    override fun map(source: Map<LoginResponseModel, String>): LoginResponseModel {
        return LoginResponseModel(
            status = source["status"].toString(),
            statusCode = source["statusCode"]?.toInt(),
            message = source["message"].toString(),
            error = source["error"].toString()
            )
    }
}