package com.egpaid.employeeapp.home.monitor.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category( @SerializedName("appname")
                     @Expose
                     val appName:String,
                     @SerializedName("packagename")
                     @Expose
                     var packageName:String,
                     @SerializedName("type")
                     @Expose
                     val type:String )