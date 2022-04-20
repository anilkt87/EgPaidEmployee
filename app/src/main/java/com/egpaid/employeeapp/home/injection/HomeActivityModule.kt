package com.egpaid.employeeapp.home.injection

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.di.qualifier.ForActivity
import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.appsusagemanager.AppsUsageManager
import com.egpaid.employeeapp.home.homedashboard.injection.HomeFragmentBinding
import com.egpaid.employeeapp.home.monitor.domain.MonitorUseCase
import com.egpaid.employeeapp.home.monitor.domain.MonitorUseCaseImpl
import com.egpaid.employeeapp.home.monitor.injection.MonitorFragmentBinding
import com.egpaid.employeeapp.home.profile.injection.ProfileFragmentBinding
import com.egpaid.employeeapp.home.view.HomeActivity
import com.egpaid.employeeapp.home.viewmodel.HomeViewModeFactory
import com.egpaid.employeeapp.home.viewmodel.HomeViewModel
import com.egpaid.employeeapp.home.viewmodel.HomeViewModelmpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(
        includes = [
            HomeFragmentBinding::class,
            MonitorFragmentBinding::class,
            ProfileFragmentBinding::class]
)
class HomeActivityModule {


    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(activityInStore: HomeActivity): Context = activityInStore

    @PerActivity
    @Provides
    @Named("PerActivity1")
    open fun provideInt(): String {
        return "hello"
    }

    @Provides
    internal fun provideUseCase(useCase: MonitorUseCaseImpl): MonitorUseCase = useCase


    @Provides
    @PerActivity
    fun provideHomeViewModel(
            homeActivity: HomeActivity,
            factory: HomeViewModeFactory
    ): HomeViewModel =
            ViewModelProvider(homeActivity, factory).get(HomeViewModel::class.java)

    @Provides
    @PerActivity
    fun bindHomeViewModel(homeViewModelmpl: HomeViewModelmpl): ViewModel =
            homeViewModelmpl

    @Provides
    @PerActivity
    fun provideStateLiveData(): MutableLiveData<BaseViewModel.State> = MutableLiveData()

    @Provides
    fun appUsageManager(context: Context) = AppsUsageManager(context)

}