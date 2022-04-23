package com.egpaid.employeeapp.menuitem.viewmodel

import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.view.entities.HomeModel
import javax.inject.Inject

class MenuViewModelImpl @Inject constructor(
    override val stateLiveData: MutableLiveData<State>,
    private val appPreference: AppPreference
) : MenuViewModel() {
    override fun getMyData(position: Int) {

        val homePageItemData: List<HomeModel>? = appPreference.getGetSideBarData()
            .filter { it.menu?.location == "Non-landing page" && it.menu?.status == 0 }
        stateLiveData.value =
            homePageItemData?.let { State.MyNavBarDetailPage(data = it[0].submenu[position]) }
    }
}