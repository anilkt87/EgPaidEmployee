package com.egpaid.employeeapp.books.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.adapter.AdapterDelegate
import com.egpaid.employeeapp.base.widget.DisplayableItem
import com.egpaid.employeeapp.books.entities.ItemsItem
import javax.inject.Inject

class BookItemDelegate @Inject constructor(
    private val layoutInflater: LayoutInflater,
    val context: Context,
) :
    AdapterDelegate<DisplayableItem>() {

    override fun isForViewType(items: MutableList<DisplayableItem>, position: Int) =
        items[position] is ItemsItem

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.view_books_item, parent, false)
        return BooksViewHolder(view, context)
    }

    override fun onBindViewHolder(
        items: MutableList<DisplayableItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val viewHolder = holder as BooksViewHolder
        viewHolder.bind(items[position] as ItemsItem)
    }
}