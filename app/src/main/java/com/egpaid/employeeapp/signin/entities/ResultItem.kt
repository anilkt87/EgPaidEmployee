package com.egpaid.employeeapp.signin.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResultItem(

        @SerializedName("website")
        @Expose
        val website: String? = null,

        @SerializedName("address")
        @Expose
        val address: String? = null,

        @SerializedName("gender")
        @Expose
        val gender: String? = null,

        @SerializedName("phone")
        @Expose
        val phone: String? = null,

        @SerializedName("_links")
        @Expose
        val links: Links? = null,

        @SerializedName("dob")
        @Expose
        val dob: String? = null,

        @SerializedName("last_name")
        @Expose
        val lastName: String? = null,

        @SerializedName("id")
        @Expose
        val id: String? = null,

        @SerializedName("first_name")
        @Expose
        val firstName: String? = null,

        @SerializedName("email")
        @Expose
        val email: String? = null,

        @SerializedName("status")
        @Expose
        val status: String? = null
)