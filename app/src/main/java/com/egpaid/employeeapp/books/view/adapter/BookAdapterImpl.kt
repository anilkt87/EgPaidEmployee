package com.egpaid.employeeapp.books.view.adapter

import com.egpaid.employeeapp.base.adapter.DiffCallback
import com.egpaid.employeeapp.books.entities.ItemsItem
import javax.inject.Inject
import javax.inject.Named

class BookAdapterImpl @Inject constructor(
    @Named("ForBookAdapter")
     diffCallback: DiffCallback,
    bookItemDelegate: BookItemDelegate
) : BookAdapter(diffCallback) {

    init {
        getDelegatesManager().addDelegate(bookItemDelegate)
    }

    override fun setBooks(books: List<ItemsItem>) {
        setItems(books)
    }
}