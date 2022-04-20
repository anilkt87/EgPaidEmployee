package com.egpaid.employeeapp.signin.domain

import com.egpaid.employeeapp.base.base.schedulers.BaseSchedulerProvider
import com.egpaid.employeeapp.base.domain.BaseUseCase
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.entities.LoginResponseErrorModel
import com.egpaid.employeeapp.signin.entities.LoginResponseSucessModel
import com.egpaid.employeeapp.signin.repository.SignInRepo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GetSignInUseCaseImpl @Inject constructor(
    compositeDisposable: CompositeDisposable,
    private val signInRepo: SignInRepo,
    private val schedulerProvider: BaseSchedulerProvider
) : BaseUseCase(compositeDisposable), SignInUseCase {

    private var callback: SignInUseCase.Callback? = null

    override fun execute(loginRequestModel: LoginRequestModel) {
        trackDisposable(
            signInRepo.login(loginRequestModel)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::handleSuccess, ::handlerError)
        )
    }

    override fun setCallback(callback: SignInUseCase.Callback) {
        this.callback = callback
    }

    private fun handleSuccess(resultData: LoginResponseSucessModel?) {
        callback?.onLoginSuccess(resultData)
    }

    private fun handlerError(throwable: Throwable) {
        callback?.onLoginError(throwable)
    }
}


