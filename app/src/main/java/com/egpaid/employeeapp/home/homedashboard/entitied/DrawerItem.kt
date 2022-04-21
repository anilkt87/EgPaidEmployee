package com.egpaid.employeeapp.home.homedashboard.entitied

import com.egpaid.employeeapp.home.view.entities.Menu
import com.egpaid.employeeapp.home.view.entities.Submenu
import com.google.gson.annotations.SerializedName


data class DrawerItem(
    @SerializedName("menu") var menu: Menu? = Menu(),
    @SerializedName("submenu") var submenu: ArrayList<Submenu> = arrayListOf()
)