package com.egpaid.employeeapp.home.injection

import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.home.view.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityBinding {

    @PerActivity
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun bindMainActivityBinding(): HomeActivity

}