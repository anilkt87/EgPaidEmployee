package com.egpaid.employeeapp.home.view.entities

import com.google.gson.annotations.SerializedName


data class HomeModel(
    @SerializedName("menu") var menu: Menu? = Menu(),
    @SerializedName("submenu") var submenu: ArrayList<Submenu> = arrayListOf()
)