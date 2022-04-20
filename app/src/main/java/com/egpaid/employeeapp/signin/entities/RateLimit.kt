package com.egpaid.employeeapp.signin.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RateLimit(

	@SerializedName("limit")
	@Expose
	val limit: Int? = null,

	@SerializedName("reset")
	@Expose
	val reset: Int? = null,

	@SerializedName("remaining")
	@Expose
	val remaining: Int? = null
)