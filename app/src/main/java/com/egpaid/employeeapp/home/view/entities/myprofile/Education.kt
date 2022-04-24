package com.example.example

import com.google.gson.annotations.SerializedName


data class Education (

  @SerializedName("id"         ) var id         : Int?    = null,
  @SerializedName("empid"      ) var empid      : String? = null,
  @SerializedName("degree"     ) var degree     : String? = null,
  @SerializedName("school"     ) var school     : String? = null,
  @SerializedName("board"      ) var board      : String? = null,
  @SerializedName("year"       ) var year       : String? = null,
  @SerializedName("division"   ) var division   : String? = null,
  @SerializedName("percentage" ) var percentage : String? = null

)