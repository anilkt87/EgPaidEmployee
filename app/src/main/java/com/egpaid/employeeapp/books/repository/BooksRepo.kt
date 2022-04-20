package com.egpaid.employeeapp.books.repository

import com.egpaid.employeeapp.books.entities.Books
import io.reactivex.Single

interface BooksRepo {
    fun getBooksData(): Single<Books>
}