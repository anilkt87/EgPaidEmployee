package com.egpaid.employeeapp.home.monitor.injection

import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egpaid.employeeapp.base.adapter.DiffCallback
import com.egpaid.employeeapp.base.di.qualifier.ForFragment
import com.egpaid.employeeapp.base.di.scope.PerFragment
import com.egpaid.employeeapp.base.livedata.SingleLiveData
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.monitor.view.HomeFragment
import com.egpaid.employeeapp.home.domain.MainActivityUseCase
import com.egpaid.employeeapp.home.domain.MainActivityUseCaseImpl
import com.egpaid.employeeapp.home.monitor.viewmdel.MonitorDataViewModel
import com.egpaid.employeeapp.home.monitor.viewmdel.MonitorDataViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class HomeFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    fun provideContext(fragment: HomeFragment): Context = fragment.requireContext()

    @PerFragment
    @Provides
    @Named("PerFragment")
    open fun provideInt(): String {
        return "hello"
    }

    @Provides
    internal fun provideUseCase(useCase: MainActivityUseCaseImpl): MainActivityUseCase = useCase


    @Provides
    @PerFragment
    fun provideMonitor(
        homeFragment: HomeFragment,
        factory: MonitorDataViewModelFactory
    ): MonitorDataViewModel =
            ViewModelProvider(homeFragment, factory).get(MonitorDataViewModel::class.java)

    @Provides
    @PerFragment
    fun bindMonitorViewModel(monitorViewModel: MonitorDataViewModel): ViewModel =
            monitorViewModel

    @Provides
    @PerFragment
    fun provideStateLiveData(): MutableLiveData<BaseViewModel.State> = MutableLiveData()




}