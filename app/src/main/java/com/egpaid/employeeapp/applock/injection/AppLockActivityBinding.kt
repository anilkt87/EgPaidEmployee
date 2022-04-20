package com.egpaid.employeeapp.applock.injection

import com.egpaid.employeeapp.applock.view.AppLockActivity
import com.egpaid.employeeapp.base.di.scope.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppLockActivityBinding {
    @PerActivity
    @ContributesAndroidInjector(modules = [AppLockActivityModule::class])
    abstract fun bindAppLockBinding(): AppLockActivity
}