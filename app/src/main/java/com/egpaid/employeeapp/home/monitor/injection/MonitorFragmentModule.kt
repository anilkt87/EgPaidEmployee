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
import com.egpaid.employeeapp.home.monitor.view.MonitorFragment
import com.egpaid.employeeapp.home.domain.MainActivityUseCase
import com.egpaid.employeeapp.home.domain.MainActivityUseCaseImpl
import com.egpaid.employeeapp.home.monitor.viewmdel.MonitorDataViewModel
import com.egpaid.employeeapp.home.monitor.viewmdel.MonitorDataViewModelFactory
import com.egpaid.employeeapp.home.monitor.widget.MonitorGraphWidget
import com.egpaid.employeeapp.home.monitor.widget.MonitorGraphWidgetImpl
import com.egpaid.employeeapp.home.monitor.widget.MonitorListIWidget
import com.egpaid.employeeapp.home.monitor.widget.MonitorListIWidgetImpl
import com.egpaid.employeeapp.home.monitor.widget.adapter.MonitorDailyAdapter
import com.egpaid.employeeapp.home.monitor.widget.adapter.MonitorDailyAdapterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MonitorFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    fun provideContext(fragment: MonitorFragment): Context = fragment.requireContext()

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
            monitorFragment: MonitorFragment,
            factory: MonitorDataViewModelFactory
    ): MonitorDataViewModel =
            ViewModelProvider(monitorFragment, factory).get(MonitorDataViewModel::class.java)

    @Provides
    @PerFragment
    fun bindMonitorViewModel(monitorViewModel: MonitorDataViewModel): ViewModel =
            monitorViewModel

    @Provides
    @PerFragment
    fun provideStateLiveData(): MutableLiveData<BaseViewModel.State> = MutableLiveData()

    @Provides
    @PerFragment
    @Named("ForMonitorDailyAdapter")
    fun provideLinearLayoutManager(@ForFragment context: Context): RecyclerView.LayoutManager = LinearLayoutManager(context)

    @Provides
    @PerFragment
    @Named("ForMonitorDailyAdapter")
    fun provideDiffCallback(): DiffCallback = DiffCallback()

    @Provides
    @PerFragment
    fun provideMonitorAdapter(adapter: MonitorDailyAdapterImpl): MonitorDailyAdapter = adapter

    @Provides
    @PerFragment
    fun provideMonitorWidget(widget: MonitorListIWidgetImpl): MonitorListIWidget = widget


    @Provides
    @PerFragment
    fun provideMonitorDailyGraphWidgetWidget(widget: MonitorGraphWidgetImpl): MonitorGraphWidget = widget

    @Provides
    @PerFragment
    fun provideLayoutInflater(@ForFragment context: Context): LayoutInflater = LayoutInflater.from(context)

    @Provides
    @PerFragment
    fun provideOnClickedLiveData(): SingleLiveData<MonitorListIWidget.CallToAction> = SingleLiveData()

}