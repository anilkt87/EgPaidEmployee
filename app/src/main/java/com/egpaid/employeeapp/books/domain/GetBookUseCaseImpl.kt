package com.egpaid.employeeapp.books.domain

import com.egpaid.employeeapp.base.base.schedulers.BaseSchedulerProvider
import com.egpaid.employeeapp.base.domain.BaseUseCase
import com.egpaid.employeeapp.books.entities.Books
import com.egpaid.employeeapp.books.repository.BooksRepo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetBookUseCaseImpl @Inject constructor(
    compositeDisposable: CompositeDisposable,
    private val booksRepo: BooksRepo,
    private val schedulerProvider: BaseSchedulerProvider
) : BaseUseCase(compositeDisposable), GetBookUseCase {
    private var callback: GetBookUseCase.Callback? = null

    override fun setCallback(callback: GetBookUseCase.Callback) {
        this.callback = callback
    }

    override fun execute() {
        trackDisposable(
            booksRepo
                .getBooksData()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.computation())
                .map { Books(it.totalItems,it.kind,it.items) }
                .observeOn(schedulerProvider.ui())
                .subscribe(::onSuccess, ::onError))
    }
    private fun onSuccess(books: Books) {
        callback?.onBooksLoaded(books)
    }

    private fun onError(throwable: Throwable) {
        callback?.onBooksLoadingError(throwable)
    }

    override fun cleanup() {
        callback = null
        super.cleanup()
    }

}