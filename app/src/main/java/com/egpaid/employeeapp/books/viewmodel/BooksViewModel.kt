package com.egpaid.employeeapp.books.viewmodel

import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel

abstract class BooksViewModel : BaseViewModel() {
    abstract val stateLiveData: LiveData<State>
    abstract val personResponseLiveData: LiveData<State>
    abstract fun getPersonData()
}