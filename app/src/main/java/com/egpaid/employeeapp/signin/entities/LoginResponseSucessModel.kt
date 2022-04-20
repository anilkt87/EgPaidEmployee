package com.egpaid.employeeapp.signin.entities

import com.example.example.User
import com.google.gson.annotations.SerializedName


data class LoginResponseSucessModel (@SerializedName("status") var status : String? = null, @SerializedName("statusCode") var statusCode : Int?    = null, @SerializedName("user") var user : User?   = User(), @SerializedName("token") var token : String? = null)