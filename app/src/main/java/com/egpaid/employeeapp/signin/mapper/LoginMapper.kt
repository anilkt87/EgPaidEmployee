package com.egpaid.employeeapp.signin.mapper

import com.egpaid.employeeapp.base.basemapper.Mapper
import com.egpaid.employeeapp.signin.entities.LoginResponseErrorModel
import javax.inject.Inject

class LoginMapper @Inject constructor() : Mapper<Map<LoginResponseErrorModel, String>, LoginResponseErrorModel> {
    override fun map(source: Map<LoginResponseErrorModel, String>): LoginResponseErrorModel {
        return LoginResponseErrorModel(
            status = source["status"].toString(),
            statusCode = source["statusCode"]?.toInt(),
            message = source["message"].toString(),
            error = source["error"].toString()
            )
    }
}