package com.egpaid.employeeapp.appsecurity.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.appsecurity.view.AppSecurityActivity
import com.egpaid.employeeapp.appsecurity.viewmodel.AppSecurityViewModel
import com.egpaid.employeeapp.appsecurity.viewmodel.AppSecurityViewModelFactory
import com.egpaid.employeeapp.appsecurity.viewmodel.AppSecurityViewModelImpl
import com.egpaid.employeeapp.appsecurity.widget.AppSecurityWidget
import com.egpaid.employeeapp.appsecurity.widget.AppSecurityWidgetImpl
import com.egpaid.employeeapp.base.di.qualifier.ForActivity
import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.base.livedata.SingleLiveData
import com.egpaid.employeeapp.signin.view.view.SigninActivity
import com.egpaid.employeeapp.signin.view.widget.SignInWidget
import com.egpaid.employeeapp.signin.view.widget.SignInWidgetImpl
import com.egpaid.employeeapp.signin.viewmodel.SignInViewModel
import com.egpaid.employeeapp.signin.viewmodel.SignInViewModelFactory
import com.egpaid.employeeapp.signin.viewmodel.SignInViewModelImpl
import dagger.Module
import dagger.Provides

@Module
class AppSecurityActivityModule {
    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(appSecurityActivity: AppSecurityActivity): Context = appSecurityActivity


    @Provides
    @PerActivity
    fun provideViewModel(
        appSecurityActivity: AppSecurityActivity,
        factory: AppSecurityViewModelFactory
    ): AppSecurityViewModel =
        ViewModelProvider(appSecurityActivity, factory).get(AppSecurityViewModel::class.java)

    @Provides
    @PerActivity
    fun bindSigninViewModel(appSecurityViewModelImpl: AppSecurityViewModelImpl): ViewModel =
        appSecurityViewModelImpl

    @Provides
    @PerActivity
    fun provideAppSecurityWidget(appSecurityWidgetImpl: AppSecurityWidgetImpl): AppSecurityWidget = appSecurityWidgetImpl

    @Provides
    @PerActivity
    fun provideOnClickedLiveData(): SingleLiveData<AppSecurityWidget.CallToAction> = SingleLiveData()
}