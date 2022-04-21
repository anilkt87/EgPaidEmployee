package com.egpaid.employeeapp.signin.domain

import com.egpaid.employeeapp.base.base.schedulers.BaseSchedulerProvider
import com.egpaid.employeeapp.base.domain.BaseUseCase
import com.egpaid.employeeapp.signin.entities.AppSettingResponse
import com.egpaid.employeeapp.signin.repository.SignInRepo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppSettingUseCaseImpl @Inject constructor(
    compositeDisposable: CompositeDisposable,
    private val signInRepo: SignInRepo,
    private val schedulerProvider: BaseSchedulerProvider
) : BaseUseCase(compositeDisposable), AppSettingUseCase {

    private var callback: AppSettingUseCase.Callback? = null

    override fun execute(token: String) {
        trackDisposable(
            signInRepo.getAppSetting(token)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::handleAppSettingSuccess, ::handlerAppSettingError)
        )
    }

    override fun setCallback(callback: AppSettingUseCase.Callback) {
        this.callback = callback
    }

    private fun handleAppSettingSuccess(resultData: AppSettingResponse?) {
        callback?.onAppSettingSuccess(resultData)
    }

    private fun handlerAppSettingError(throwable: Throwable) {
        callback?.onAppSettingError(throwable)
    }
}