package com.example.example

import com.google.gson.annotations.SerializedName


data class User (

  @SerializedName("name"        ) var name        : String? = null,
  @SerializedName("profileimg"  ) var profileimg  : String? = null,
  @SerializedName("empid"       ) var empid       : String? = null,
  @SerializedName("designation" ) var designation : String? = null,
  @SerializedName("Status"      ) var Status      : String? = null

)