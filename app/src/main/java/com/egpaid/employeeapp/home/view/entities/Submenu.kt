package com.egpaid.employeeapp.home.view.entities

import com.google.gson.annotations.SerializedName


data class Submenu(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("usertype") var usertype: String? = null,
    @SerializedName("pagename") var pagename: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("location") var location: String? = null,
    @SerializedName("treeview") var treeview: Int? = null,
    @SerializedName("parent") var parent: Int? = null,
    @SerializedName("orderby") var orderby: Int? = null,
    @SerializedName("status") var status: Int? = null

)