package com.egpaid.employeeapp.home.domain

import com.egpaid.employeeapp.base.base.schedulers.BaseSchedulerProvider
import com.egpaid.employeeapp.base.domain.BaseUseCase
import com.egpaid.employeeapp.home.repostries.MainActivityRepo
import com.egpaid.employeeapp.home.view.entities.HomeModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityUseCaseImpl @Inject constructor(
    private val mainActivityRepo: MainActivityRepo,
    compositeDisposable: CompositeDisposable,
    private val schedulerProvider: BaseSchedulerProvider
) : BaseUseCase(compositeDisposable), MainActivityUseCase {

    private var callback: MainActivityUseCase.Callback? = null

    override fun execute(token: String) {
        trackDisposable(
            mainActivityRepo.getMyAppSideBar(token)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(::handleMyAppSideBarSuccess, ::handlerMyAppSideBarError)
        )
    }

    override fun setCallback(callback: MainActivityUseCase.Callback) {
        this.callback = callback
    }

    private fun handleMyAppSideBarSuccess(resultData: List<HomeModel>) {
        callback?.onMySideBarSuccess(resultData)
    }

    private fun handlerMyAppSideBarError(throwable: Throwable) {
        callback?.onMySideBarError(throwable)
    }
    override fun cleanup() {
        super.cleanup()
        callback = null
    }

}