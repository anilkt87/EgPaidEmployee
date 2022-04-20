package com.egpaid.employeeapp.signin.viewmodel

import androidx.lifecycle.MediatorLiveData
import com.egpaid.employeeapp.signin.domain.SignInUseCase
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseModel
import javax.inject.Inject

class SignInViewModelImpl @Inject constructor(
    private val getSignInUseCase: SignInUseCase,
    override val signInLiveData: MediatorLiveData<State>
) : SignInViewModel(), SignInUseCase.Callback {

    init {
        getSignInUseCase.setCallback(this)

    }


    override fun getLoginData(loginRequestModel: LoginRequestModel) {
        getSignInUseCase.execute(loginRequestModel)
    }

    override fun onLoginSuccess(response: LoginResponseModel) {
        signInLiveData.value = State.LoginSuccess
    }

    override fun onLoginError(error: Throwable) {
        signInLiveData.value = State.Error(error)
    }


}