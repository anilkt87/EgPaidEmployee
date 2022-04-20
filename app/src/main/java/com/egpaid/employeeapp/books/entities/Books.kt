package com.egpaid.employeeapp.books.entities

import com.google.gson.annotations.SerializedName

data class Books(

	@field:SerializedName("totalItems")
	val totalItems: Int? = null,

	@field:SerializedName("kind")
	val kind: String? = null,

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)