package com.egpaid.employeeapp.home.homedashboard.injection

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.di.qualifier.ForFragment
import com.egpaid.employeeapp.base.di.scope.PerFragment
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.home.homedashboard.DrawerFragment
import com.egpaid.employeeapp.home.homedashboard.drawberviewmodel.DrawableViewModel
import com.egpaid.employeeapp.home.homedashboard.drawberviewmodel.DrawableViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DrawberFragmentModul {
    @Provides
    @PerFragment
    @ForFragment
    fun provideContext(fragment: DrawerFragment): Context = fragment.requireContext()


    @Provides
    @PerFragment
    fun provideDrawble(
        fragment: DrawerFragment,
        factory: DrawableViewModelFactory
    ): DrawableViewModel =
        ViewModelProvider(fragment, factory).get(DrawableViewModel::class.java)

    @Provides
    @PerFragment
    fun bindDrawbleViewModel(monitorViewModel: DrawableViewModel): ViewModel =
        monitorViewModel

    @Provides
    @PerFragment
    fun provideStateLiveData(): MutableLiveData<BaseViewModel.State> = MutableLiveData()
}