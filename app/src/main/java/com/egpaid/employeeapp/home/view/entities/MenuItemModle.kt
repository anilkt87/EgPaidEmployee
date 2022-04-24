package com.egpaid.employeeapp.home.view.entities


data class MenuItemModle(
    var id: Int? = null,
    var usertype: String? = null,
    var pagename: String? = null,
    var icon: String? = null,
    var location: String? = null,
    var treeview: Int? = null,
    var parent: Int? = null,
    var orderby: Int? = null,
    var status: Int? = null
)