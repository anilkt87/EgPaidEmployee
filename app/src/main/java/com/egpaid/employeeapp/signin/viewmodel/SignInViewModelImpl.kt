package com.egpaid.employeeapp.signin.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.signin.domain.SignInUseCase
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseErrorModel
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel
import javax.inject.Inject

class SignInViewModelImpl @Inject constructor(
    private val getSignInUseCase: SignInUseCase,
    override val signInLiveData: MediatorLiveData<State>,
    val appPreference: AppPreference
) : SignInViewModel(), SignInUseCase.Callback {
    init {
        getSignInUseCase.setCallback(this)

    }

    override fun getLoginData(loginRequestModel: LoginRequestModel) {
        getSignInUseCase.execute(loginRequestModel)
    }

    override fun onLoginSuccess(response: LoginResponseSucessModel?) {
        if (response?.status.equals("success") && response?.statusCode == 0) {
            signInLiveData.value = State.LoginSuccess(response)
        } else if (response?.status.equals("success") && response?.statusCode == 1) {
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


}