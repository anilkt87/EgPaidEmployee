package com.egpaid.employeeapp.menuitem.injection

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.di.qualifier.ForActivity
import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.menuitem.view.MenuActivity
import com.egpaid.employeeapp.menuitem.viewmodel.MenuViewModel
import com.egpaid.employeeapp.menuitem.viewmodel.MenuViewModelFactory
import com.egpaid.employeeapp.menuitem.viewmodel.MenuViewModelImpl
import com.egpaid.employeeapp.signin.view.view.SigninActivity
import com.egpaid.employeeapp.signin.viewmodel.SignInViewModel
import com.egpaid.employeeapp.signin.viewmodel.SignInViewModelFactory
import com.egpaid.employeeapp.signin.viewmodel.SignInViewModelImpl
import dagger.Module
import dagger.Provides


@Module
class MenuActivityModule {
    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(menuActivity: MenuActivity): Context = menuActivity


    @Provides
    @PerActivity
    fun provideViewModel(
        menuActivity: MenuActivity,
        factory: MenuViewModelFactory
    ): MenuViewModel =
        ViewModelProvider(menuActivity, factory).get(MenuViewModel::class.java)

    @Provides
    @PerActivity
    fun bindPersonViewModel(menuViewModelImpl: MenuViewModelImpl): ViewModel =
        menuViewModelImpl

    @Provides
    @PerActivity
    fun provideStateLiveData(): MutableLiveData<BaseViewModel.State> = MutableLiveData()
}