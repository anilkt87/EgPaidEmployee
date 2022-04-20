package com.egpaid.employeeapp.books.domain

import com.egpaid.employeeapp.base.base.domain.UseCase
import com.egpaid.employeeapp.books.entities.Books

interface GetBookUseCase : UseCase {

    interface Callback {

        fun onBooksLoaded(books: Books)

        fun onBooksLoadingError(throwable: Throwable)

    }

    fun setCallback(callback: Callback)
    fun execute()
}