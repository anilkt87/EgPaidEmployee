package com.egpaid.employeeapp.signin.entities

import com.google.gson.annotations.SerializedName

class AppSettingResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("usertype") var usertype: String? = null,
    @SerializedName("appupdate") var appupdate: Int? = null,
    @SerializedName("notification") var notification: String? = null,
    @SerializedName("maintanance") var maintanance: Int? = null,
    @SerializedName("aapupdateimage") var aapupdateimage: String? = null,
    @SerializedName("maintananceimage") var maintananceimage: String? = null,
    @SerializedName("appoldversion") var appoldversion: String? = null,
    @SerializedName("appversion") var appversion: String? = null,
    @SerializedName("androidlink") var androidlink: String? = null,
    @SerializedName("ioslink") var ioslink: String? = null

)