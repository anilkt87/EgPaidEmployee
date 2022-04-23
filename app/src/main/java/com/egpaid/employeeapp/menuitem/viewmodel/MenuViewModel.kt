package com.egpaid.employeeapp.menuitem.viewmodel

import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel

abstract class MenuViewModel : BaseViewModel() {
    abstract val stateLiveData: LiveData<State>
    abstract fun getMyData(position: Int)
}