package com.egpaid.employeeapp.signin.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Links(

	@SerializedName("edit")
	@Expose
	val edit: Edit? = null,

	@SerializedName("self")
	@Expose
	val self: Self? = null,
	@Expose
	@SerializedName("avatar")
	val avatar: Avatar? = null
)