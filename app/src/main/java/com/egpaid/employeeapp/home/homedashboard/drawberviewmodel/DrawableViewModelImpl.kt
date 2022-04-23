package com.egpaid.employeeapp.home.homedashboard.drawberviewmodel

import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.home.view.entities.HomeModel
import com.egpaid.employeeapp.home.view.entities.Submenu

class DrawableViewModelImpl(
    override val stateLiveData: MutableLiveData<State>,
    private val appPreference: AppPreference
) : DrawableViewModel() {
    override fun getMyNaveBarNavPage() {
        val homePageItemData: List<HomeModel>? = appPreference.getGetSideBarData()
            .filter { it.menu?.location == "Non-landing page" && it.menu?.status == 0 }
        val subDefaultItem = Submenu(0, "", "", "", "", 0, 0, 0, 0)
        val list =
            homePageItemData?.let { it[0].submenu }
        list?.add(0, subDefaultItem)

        stateLiveData.value =
            homePageItemData?.let { State.MyNaveBarNavePage(data = it[0].submenu) }
    }
}
