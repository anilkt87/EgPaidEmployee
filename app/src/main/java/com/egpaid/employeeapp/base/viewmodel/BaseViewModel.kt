package com.egpaid.employeeapp.base.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.egpaid.employeeapp.home.monitor.entities.Monitor
import com.egpaid.employeeapp.home.view.entities.HomeModel
import com.egpaid.employeeapp.home.view.entities.Menu
import com.egpaid.employeeapp.home.view.entities.MenuItemModle
import com.egpaid.employeeapp.home.view.entities.Submenu
import com.egpaid.employeeapp.home.view.entities.myprofile.MyProfile
import com.egpaid.employeeapp.signin.entities.AppSettingResponse
import com.egpaid.employeeapp.signin.entities.LoginResponseErrorModel
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    sealed class State {
        data class Success(val data: Any) : State()
        class SuccessListData(val response: List<Any>) : State()
        class SuccessDailyCategoryData(val data: HashMap<String, ArrayList<Monitor>>) : State()
        class Error(error: Throwable) : State()
        object EmptyData : State()
        object Loading : State()
        object NetworkError : State()
        object GeneralError : State()

        object AlreadySignIn : State()
        object RedirectToAppLocaPage : State()

        object ENTER_PIN : State()
        object IncorrectPattern : State()
        object CREATE_PIN : State()
        object INCORRECT_PIN : State()

        object PinOption : State()
        object PatternOption : State()
        object BiometricOption : State()
        data class LoginSuccess(val loginResponseSucessModel: LoginResponseSucessModel) : State()
        data class LoginError(val loginResponseErrorModel: LoginResponseErrorModel) : State()
        data class AppSettingSuccess(val appSettingResponse: AppSettingResponse?) : State()
        data class MySideBarData(val data: List<HomeModel>) : State()
        data class MySideBarHomePage(val data: List<HomeModel>) : State()
        data class MyNaveBarNavePage(val data: ArrayList<MenuItemModle>) : State()
        data class MyNavBarDetailPage(val data: Submenu) : State()
        data class MyHomePageItemSelected(val data: Menu?) : State()
        data class MyProfileResponse(val data: MyProfile) : State()

    }


}