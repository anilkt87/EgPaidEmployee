package com.egpaid.employeeapp.home.injection

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.di.qualifier.ForActivity
import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.homedashboard.injection.DrawberFragmentBinding
import com.egpaid.employeeapp.home.domain.MainActivityUseCase
import com.egpaid.employeeapp.home.domain.MainActivityUseCaseImpl
import com.egpaid.employeeapp.home.monitor.injection.HomeFragmentBinding
import com.egpaid.employeeapp.home.repostries.MainActivityRepo
import com.egpaid.employeeapp.home.repostries.MainActivityRepoImpl
import com.egpaid.employeeapp.home.view.MainActivity
import com.egpaid.employeeapp.home.viewmodel.HomeViewModeFactory
import com.egpaid.employeeapp.home.viewmodel.HomeViewModel
import com.egpaid.employeeapp.home.viewmodel.HomeViewModelImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module(
    includes = [
        DrawberFragmentBinding::class,
        HomeFragmentBinding::class]
)
class MainActivityModule {


    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(activityInStore: MainActivity): Context = activityInStore

    @PerActivity
    @Provides
    @Named("PerActivity1")
    open fun provideInt(): String {
        return "hello"
    }

    @Provides
    internal fun provideUseCase(useCase: MainActivityUseCaseImpl): MainActivityUseCase = useCase

    @Provides
    fun provideMainRepository(mainActivityRepo: MainActivityRepoImpl): MainActivityRepo =
        mainActivityRepo

    @Provides
    @PerActivity
    fun provideHomeViewModel(
        mainActivity: MainActivity,
        factory: HomeViewModeFactory
    ): HomeViewModel =
        ViewModelProvider(mainActivity, factory).get(HomeViewModel::class.java)

    @Provides
    @PerActivity
    fun bindHomeViewModel(homeViewModelImpl: HomeViewModelImpl): ViewModel =
        homeViewModelImpl

    @Provides
    @PerActivity
    fun provideStateLiveData(): MutableLiveData<BaseViewModel.State> = MutableLiveData()


}