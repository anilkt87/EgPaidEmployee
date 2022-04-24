package com.egpaid.employeeapp.home.homedashboard.drawberviewmodel

import androidx.lifecycle.MutableLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.home.view.entities.HomeModel
import com.egpaid.employeeapp.home.view.entities.MenuItemModle
import com.egpaid.employeeapp.home.view.entities.Submenu

class DrawableViewModelImpl(
    override val stateLiveData: MutableLiveData<State>,
    private val appPreference: AppPreference
) : DrawableViewModel() {
    override fun getMyNaveBarNavPage() {
        val menuItemList = arrayListOf<MenuItemModle>()


        val homePageItemData: List<HomeModel>? = appPreference.getGetSideBarData()
            .filter { it.menu?.status == 0 }


        homePageItemData?.let {
            for (item in it) {
                if (item.submenu.size > 0) {
                    for (subitem in item.submenu) {
                        menuItemList.add(
                            MenuItemModle(
                                subitem.id,
                                subitem.usertype,
                                subitem.pagename,
                                subitem.icon,
                                subitem.location,
                                subitem.treeview,
                                subitem.parent,
                                subitem.orderby,
                                subitem.status
                            )
                        )
                    }

                }
                menuItemList.add(
                    MenuItemModle(
                        item.menu?.id,
                        item.menu?.usertype,
                        item.menu?.pagename,
                        item.menu?.icon,
                        item.menu?.location,
                        item.menu?.treeview,
                        item.menu?.parent,
                        item.menu?.orderby,
                        item.menu?.status
                    )
                )


            }
        }


        val subDefaultItem = MenuItemModle(0, "", "", "", "", 0, 0, 0, 0)

        menuItemList?.add(0, subDefaultItem)
        stateLiveData.value = State.MyNaveBarNavePage(menuItemList)
    }
}
