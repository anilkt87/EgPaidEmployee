package com.egpaid.employeeapp.books.repository

import com.egpaid.employeeapp.books.entities.Books
import com.egpaid.employeeapp.base.dataservice.remote.RetrofitService
import io.reactivex.Single
import javax.inject.Inject

class BooksRepoImpl @Inject constructor(
    val api: RetrofitService
) : BooksRepo {

    override fun getBooksData(): Single<Books> {

        return api.getBooks("harry", "rowling")

    }
}