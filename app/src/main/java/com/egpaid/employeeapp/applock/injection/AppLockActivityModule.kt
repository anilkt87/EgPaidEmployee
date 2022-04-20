package com.egpaid.employeeapp.applock.injection

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.applock.view.AppLockActivity
import com.egpaid.employeeapp.applock.viewmodel.AppLockViewModel
import com.egpaid.employeeapp.applock.viewmodel.AppLockViewModelFactory
import com.egpaid.employeeapp.applock.viewmodel.AppLockViewModelmpl
import com.egpaid.employeeapp.applock.widget.AppLockWidget
import com.egpaid.employeeapp.applock.widget.AppLockWidget.*
import com.egpaid.employeeapp.applock.widget.AppLockWidgetImpl
import com.egpaid.employeeapp.base.di.qualifier.ForActivity
import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.base.livedata.SingleLiveData
import dagger.Module
import dagger.Provides

@Module
class AppLockActivityModule {

    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(activityInStore: AppLockActivity): Context = activityInStore

    @Provides
    @PerActivity
    fun provideAPPLockWidget(appLockWidget: AppLockWidgetImpl): AppLockWidget = appLockWidget

    @Provides
    @PerActivity
    fun provideOnClickedLiveData(): SingleLiveData<CallToAction> = SingleLiveData()


    @Provides
    @PerActivity
    fun provideAPpLocakViewModel(
        appLockActivity: AppLockActivity,
        factory: AppLockViewModelFactory
    ): AppLockViewModel =
        ViewModelProvider(appLockActivity, factory).get(AppLockViewModel::class.java)

    @Provides
    @PerActivity
    fun bindAppLocakViewModel(appLockViewModel: AppLockViewModelmpl): ViewModel =
        appLockViewModel

//    @Provides
//    @PerActivity
//    fun provideStateLiveData(): MutableLiveData<State> = MutableLiveData()

}