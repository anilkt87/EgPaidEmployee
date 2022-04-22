package com.egpaid.employeeapp.home.monitor.injection

import com.egpaid.employeeapp.base.di.scope.PerFragment
import com.egpaid.employeeapp.home.monitor.view.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBinding {
    @PerFragment
    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun bindMonitorFragment(): HomeFragment
}