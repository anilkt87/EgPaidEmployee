package com.egpaid.employeeapp.home.monitor.viewmdel

import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.home.view.entities.HomeModel
import com.egpaid.employeeapp.home.view.entities.Menu

class HomeDataViewModelImpl(
    override val stateLiveData: MutableLiveData<State>,
    private val appPreference: AppPreference
) : HomeDataViewModel() {
    override fun getMySideBarOnHomePageItem() {
        val homePageItemData: List<HomeModel>? = appPreference.getGetSideBarData()
            .filter { it.menu?.location == "Landing page" && it.menu?.status == 0 }
        stateLiveData.value = homePageItemData?.let { State.MySideBarHomePage(data = it) }
    }

    override fun getItemData(position: Int) {
        val homePageSelectedItemData: Menu? = appPreference.getGetSideBarData()
            .filter { it.menu?.location == "Landing page" && it.menu?.status == 0 }[position].menu
        stateLiveData.value = State.MyHomePageItemSelected(homePageSelectedItemData)

    }

    override fun bindMyProfileData() {
     stateLiveData.value = State.MyProfileResponse(appPreference.getMyProfileData())
    }
}



