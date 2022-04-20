package com.egpaid.employeeapp.books.entities

import com.google.gson.annotations.SerializedName

data class Epub(

	@field:SerializedName("isAvailable")
	val isAvailable: Boolean? = null,

	@field:SerializedName("acsTokenLink")
	val acsTokenLink: String? = null
)