package com.egpaid.employeeapp.home.homedashboard.injection

import com.egpaid.employeeapp.base.di.scope.PerFragment
import com.egpaid.employeeapp.home.homedashboard.DrawerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DrawberFragmentBinding {
    @PerFragment
    @ContributesAndroidInjector(modules = [DrawberFragmentModul::class])
    abstract fun bindHomeFragmentFragment(): DrawerFragment
}