package com.egpaid.employeeapp.home.homedashboard.drawberviewmodel

import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel

abstract class DrawableViewModel : BaseViewModel() {
    abstract val stateLiveData: LiveData<State>
    abstract fun getMyNaveBarNavPage()
}