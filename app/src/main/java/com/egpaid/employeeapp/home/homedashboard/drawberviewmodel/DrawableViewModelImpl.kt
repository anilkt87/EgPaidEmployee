package com.egpaid.employeeapp.home.homedashboard.drawberviewmodel

import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.home.view.entities.HomeModel

class DrawableViewModelImpl(
    override val stateLiveData: MutableLiveData<State>,
    private val appPreference: AppPreference
) : DrawableViewModel() {
    override fun getMyNaveBarNavPage() {
        val homePageItemData: List<HomeModel>? = appPreference.getGetSideBarData()
            .filter { it.menu?.location == "Non-landing page" && it.menu?.status == 0 }

        stateLiveData.value =
            homePageItemData?.let { State.MyNaveBarNavePage(data = it[0].submenu) }
    }
}
