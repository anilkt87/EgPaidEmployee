package com.egpaid.employeeapp.home.monitor.viewmdel

import androidx.lifecycle.LiveData
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel

abstract class HomeDataViewModel :BaseViewModel() {

    abstract val stateLiveData: LiveData<State>
    abstract fun getMySideBarOnHomePageItem()
    abstract fun getItemData(position:Int)
    abstract fun bindMyProfileData()
}