package com.egpaid.employeeapp.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.home.domain.MainActivityUseCase
import com.egpaid.employeeapp.home.view.entities.HomeModel
import javax.inject.Inject

class MainActivityViewModelImpl @Inject constructor(
    override val stateLiveData: MutableLiveData<State>,
    private val appPreference: AppPreference,
    private val mainActivityUseCase: MainActivityUseCase
) : MainActivityViewModel(), MainActivityUseCase.Callback {

    init {
        mainActivityUseCase.setCallback(this)
        stateLiveData.value = State.Loading
    }

    override fun getMyAppSideBar() {
        val token = appPreference.getUserData()?.token
        mainActivityUseCase.execute(token.toString())
    }


    override fun onMySideBarSuccess(response: List<HomeModel>) {
        appPreference.saveMySideBarData(response)
        stateLiveData.value = State.MySideBarData(appPreference.getGetSideBarData())
    }

    override fun onMySideBarError(error: Throwable) {
        stateLiveData.value = State.Error(error)
    }


}