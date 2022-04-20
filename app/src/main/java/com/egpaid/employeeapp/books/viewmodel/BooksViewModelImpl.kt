package com.egpaid.employeeapp.books.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.extenstion.isNetworkError
import com.egpaid.employeeapp.books.domain.GetBookUseCase
import com.egpaid.employeeapp.books.entities.Books
import javax.inject.Inject

class BooksViewModelImpl @Inject constructor(
    private val getBookUseCase: GetBookUseCase,
    override val personResponseLiveData: MediatorLiveData<State>,
    override val stateLiveData: MutableLiveData<State>,
) : BooksViewModel(), GetBookUseCase.Callback {

    init {
        getBookUseCase.setCallback(this)
    }

    override fun getPersonData() {
        getBookUseCase.execute()
    }

    override fun onBooksLoaded(books: Books) {
        Log.d("Datat", "Books" + books.items)
        stateLiveData.value = State.Success(books)
    }

    override fun onBooksLoadingError(throwable: Throwable) {
        if (throwable.isNetworkError()) {
            stateLiveData.value = State.NetworkError
        } else {
            stateLiveData.value = State.GeneralError
        }
    }



}