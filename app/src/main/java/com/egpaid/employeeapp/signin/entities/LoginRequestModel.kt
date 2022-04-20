package com.egpaid.employeeapp.signin.entities

import com.google.gson.annotations.SerializedName

data class LoginRequestModel(@SerializedName("loginId") var loginId: String? = null, @SerializedName("password") var password: String? = null)