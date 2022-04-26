package com.egpaid.employeeapp.merchentonboarding.entities

import com.google.gson.annotations.SerializedName


data class SearchMearchantRequestModel (

  @SerializedName("mobile"       ) var mobile       : String? = null,
  @SerializedName("merchantType" ) var merchantType : String? = null,
  @SerializedName("latitude"     ) var latitude     : String? = null,
  @SerializedName("longitude"    ) var longitude    : String? = null

)