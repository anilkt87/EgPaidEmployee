package com.egpaid.employeeapp.books.view.adapter

import com.egpaid.employeeapp.base.adapter.DiffCallback
import com.egpaid.employeeapp.base.adapter.DisplayableAdapter
import com.egpaid.employeeapp.books.entities.ItemsItem

abstract class BookAdapter(diffCallback: DiffCallback) : DisplayableAdapter(diffCallback) {
    abstract fun setBooks(books: List<ItemsItem>)
}