package com.egpaid.employeeapp.signin.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Meta(

        @SerializedName("pageCount")
        @Expose
        val pageCount: Int? = null,

        @SerializedName("code")
        @Expose
        val code: Int? = null,

        @SerializedName("perPage")
        @Expose
        val perPage: Int? = null,

        @SerializedName("rateLimit")
        @Expose
        val rateLimit: RateLimit? = null,

        @SerializedName("success")
        @Expose
        val success: Boolean? = null,

        @SerializedName("message")
        @Expose
        val message: String? = null,

        @SerializedName("totalCount")
        @Expose
        val totalCount: Int? = null,

        @SerializedName("currentPage")
        @Expose
        val currentPage: Int? = null
)