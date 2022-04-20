package com.egpaid.employeeapp.base.di.module

import com.egpaid.employeeapp.base.apppreferences.AppPreference
import com.egpaid.employeeapp.base.apppreferences.AppPreferenceImpl
import dagger.Binds
import dagger.Module

@Module
abstract  class PersistanceModule {

    @Binds
    abstract fun bindSharedPreferences(appPreferenceImpl: AppPreferenceImpl): AppPreference
}