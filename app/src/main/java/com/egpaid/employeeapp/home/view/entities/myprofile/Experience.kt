package com.example.example

import com.google.gson.annotations.SerializedName


data class Experience (

  @SerializedName("id"      ) var id      : Int?    = null,
  @SerializedName("empid"   ) var empid   : String? = null,
  @SerializedName("title"   ) var title   : String? = null,
  @SerializedName("company" ) var company : String? = null,
  @SerializedName("dates"   ) var dates   : String? = null

)