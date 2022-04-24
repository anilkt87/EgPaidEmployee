package com.egpaid.employeeapp.appsecurity.injection

import com.egpaid.employeeapp.appsecurity.view.AppSecurityActivity
import com.egpaid.employeeapp.base.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract  class AppSecurityActivityBinding {
    @PerActivity
    @ContributesAndroidInjector(modules = [AppSecurityActivityModule::class])
    abstract fun bindAppSecutiryBinding(): AppSecurityActivity
}