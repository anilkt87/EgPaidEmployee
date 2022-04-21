package com.egpaid.employeeapp.signin.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.signin.domain.AppSettingUseCase
import com.egpaid.employeeapp.signin.domain.SignInUseCase
import com.egpaid.employeeapp.signin.entities.AppSettingResponse
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseErrorModel
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel
import javax.inject.Inject

class SignInViewModelImpl @Inject constructor(
    private val getSignInUseCase: SignInUseCase,
    override val signInLiveData: MediatorLiveData<State>,
    private val appSettingUseCase: AppSettingUseCase,
    val appPreference: AppPreference
) : SignInViewModel(), SignInUseCase.Callback, AppSettingUseCase.Callback {
    init {
        getSignInUseCase.setCallback(this)
        appSettingUseCase.setCallback(this)
    }

    override fun getLoginData(loginRequestModel: LoginRequestModel) {
        getSignInUseCase.execute(loginRequestModel)
    }

    override fun getAppSetting() {
        val token = appPreference.getUserData()?.token
        appSettingUseCase.execute(token.toString())
    }

    override fun onLoginSuccess(response: LoginResponseSucessModel?) {
        if (response?.status.equals("success") && response?.statusCode == 0) {
            appPreference.saveUserData(response)
            signInLiveData.value = State.LoginSuccess(response)
        } else if (response?.status.equals("success") && response?.statusCode == 1) {
            appPreference.saveUserData(response)
            signInLiveData.value = State.LoginSuccess(response)
        } else if (response?.status.equals("fail")) {
            val loginError =
                LoginResponseErrorModel(response?.status, response?.statusCode, "", "fail")
            signInLiveData.value = State.LoginError(loginError)
        }


    }

    override fun onLoginError(error: Throwable) {
        signInLiveData.value = State.Error(error)
    }

    override fun onAppSettingSuccess(response: AppSettingResponse?) {
        signInLiveData.value = State.AppSettingSuccess(response)
    }

    override fun onAppSettingError(error: Throwable) {
        signInLiveData.value = State.Error(error)
    }


}