package com.egpaid.employeeapp.signin.injection

import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.signin.view.view.SigninActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SiginActivityBinding {
    @PerActivity
    @ContributesAndroidInjector(modules = [SignInActivityModule::class])
    abstract fun bindMainActivityBinding(): SigninActivity


}