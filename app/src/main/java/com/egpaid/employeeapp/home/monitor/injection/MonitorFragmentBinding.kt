package com.egpaid.employeeapp.home.monitor.injection

import com.egpaid.employeeapp.base.di.scope.PerFragment
import com.egpaid.employeeapp.home.monitor.view.MonitorFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MonitorFragmentBinding {
    @PerFragment
    @ContributesAndroidInjector(modules = [MonitorFragmentModule::class])
    abstract fun bindMonitorFragment(): MonitorFragment
}