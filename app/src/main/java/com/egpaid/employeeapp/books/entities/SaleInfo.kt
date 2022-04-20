package com.egpaid.employeeapp.books.entities

import com.google.gson.annotations.SerializedName

data class SaleInfo(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("isEbook")
	val isEbook: Boolean? = null,

	@field:SerializedName("saleability")
	val saleability: String? = null,

	@field:SerializedName("offers")
	val offers: List<OffersItem?>? = null,

	@field:SerializedName("buyLink")
	val buyLink: String? = null,

	@field:SerializedName("retailPrice")
	val retailPrice: RetailPrice? = null,

	@field:SerializedName("listPrice")
	val listPrice: ListPrice? = null
)