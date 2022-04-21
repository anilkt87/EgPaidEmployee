package com.egpaid.employeeapp.signin.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.signin.domain.AppSettingUseCase
import com.egpaid.employeeapp.signin.domain.SignInUseCase
import javax.inject.Inject

class SignInViewModelFactory
@Inject constructor(
    private val getSignInUseCase: SignInUseCase,
    private val signInLiveData: MediatorLiveData<BaseViewModel.State>,
    private val appSettingUseCase: AppSettingUseCase,
    val appPreference: AppPreference
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return SignInViewModelImpl(getSignInUseCase, signInLiveData, appSettingUseCase,appPreference) as T as T

    }
}
