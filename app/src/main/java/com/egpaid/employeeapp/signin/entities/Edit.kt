package com.egpaid.employeeapp.signin.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Edit(

	@SerializedName("href")
	@Expose
	val href: String? = null
)