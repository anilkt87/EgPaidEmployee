package com.egpaid.employeeapp.home.injection

import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.home.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBinding {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivityBinding(): MainActivity

}