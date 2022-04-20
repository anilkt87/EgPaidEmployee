package com.egpaid.employeeapp.home.homedashboard.injection

import com.egpaid.employeeapp.base.di.scope.PerFragment
import com.egpaid.employeeapp.home.homedashboard.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBinding {
    @PerFragment
    @ContributesAndroidInjector(modules = [HomeFragmentModul::class])
    abstract fun bindHomeFragmentFragment(): HomeFragment
}