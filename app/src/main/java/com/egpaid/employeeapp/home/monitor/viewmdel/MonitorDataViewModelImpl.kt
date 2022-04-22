package com.egpaid.employeeapp.home.monitor.viewmdel

import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.home.view.entities.HomeModel

class MonitorDataViewModelImpl(
    override val stateLiveData: MutableLiveData<State>,
    private val appPreference: AppPreference
) : MonitorDataViewModel() {
    override fun getMySideBarOnHomePageItem() {
        val homePageItemData: List<HomeModel>? = appPreference.getGetSideBarData()
            .filter { it.menu?.location == "Landing page" && it.menu?.status == 0 }
        stateLiveData.value = homePageItemData?.let { State.MySideBarHomePage(data = it) }
    }
}



