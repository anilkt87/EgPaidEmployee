package com.egpaid.employeeapp.home.monitor.entities

import com.iamkamrul.expandablerecyclerviewlist.model.ParentListItem

data class ParentCategory(val name: String, val monitorList: List<Monitor>) : ParentListItem {
    override fun getChildItemList(): List<*> = monitorList
    override fun isInitiallyExpanded(): Boolean = false
}