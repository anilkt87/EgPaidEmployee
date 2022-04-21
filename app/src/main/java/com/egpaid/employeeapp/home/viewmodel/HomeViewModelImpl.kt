package com.egpaid.employeeapp.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.home.domain.MainActivityUseCase
import com.egpaid.employeeapp.home.view.entities.HomeModel
import javax.inject.Inject

class HomeViewModelImpl @Inject constructor(
    override val stateLiveData: MutableLiveData<State>,
    private val appPreference: AppPreference,
    private val mainActivityUseCase: MainActivityUseCase
) : HomeViewModel(), MainActivityUseCase.Callback {

    init {
        mainActivityUseCase.setCallback(this)
    }

    override fun getMyAppSideBar() {
        val token = appPreference.getUserData()?.token
        mainActivityUseCase.execute(token.toString())
    }

    override fun onMySideBarSuccess(responseError: HomeModel?) {
       Log.d("Response","Sucess")
    }

    override fun onMySideBarError(error: Throwable) {
        Log.d("Response","Error")
    }


}