package com.egpaid.employeeapp.menuitem.injection

import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.menuitem.view.MenuActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MenuActivityBinding {
    @PerActivity
    @ContributesAndroidInjector(modules = [MenuActivityModule::class])
    abstract fun bindMenuActivityBinding(): MenuActivity
}