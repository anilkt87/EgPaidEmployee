package com.egpaid.employeeapp.books.view.widget

import com.egpaid.employeeapp.base.widget.ContentStateWidget
import com.egpaid.employeeapp.books.entities.Books

interface BooksWidget : ContentStateWidget<Books> {

    enum class State {
        LOADING,
        CONTENT,
        GENERAL_ERROR,
        NETWORK_ERROR
    }
}