package com.egpaid.employeeapp.home.monitor.widget.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.monitor.entities.ParentCategory
import com.iamkamrul.expandablerecyclerviewlist.adapter.ExpandableRecyclerAdapter
import com.iamkamrul.expandablerecyclerviewlist.model.ParentListItem

class CustomExpandableListAdapter(var context: Context) : ExpandableRecyclerAdapter<CategoryViewHolder, CategoryListViewHolder>(){

    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup
    ): CategoryViewHolder {
        val view = LayoutInflater.from(parentViewGroup.context).inflate(R.layout.list_cateogry_parent_group, parentViewGroup, false)
        return CategoryViewHolder(view)
    }

    override fun onCreateChildViewHolder(parentViewGroup: ViewGroup): CategoryListViewHolder {
        val view = LayoutInflater.from(parentViewGroup.context).inflate(R.layout.list_cateogry_child_item, parentViewGroup, false)
        return CategoryListViewHolder(view,context)
    }

    override fun onBindParentViewHolder(parentViewHolder: CategoryViewHolder, position: Int, parentListItem: ParentListItem) {
        val data = parentListItem as ParentCategory
        parentViewHolder.bind(data)
    }

    override fun onBindChildViewHolder(childViewHolder: CategoryListViewHolder, position: Int, childListItem: Any) {
        val data = childListItem as Monitor
        childViewHolder.bind(data)
    }
}
