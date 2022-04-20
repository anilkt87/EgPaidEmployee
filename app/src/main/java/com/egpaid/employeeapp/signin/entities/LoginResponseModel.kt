package com.egpaid.employeeapp.signin.entities

import com.google.gson.annotations.SerializedName

data class LoginResponseModel(@SerializedName("status") var status: String? = null, @SerializedName("statusCode") var statusCode: Int? = null, @SerializedName("message") var message: String? = null, @SerializedName("error") var error: String? = null)