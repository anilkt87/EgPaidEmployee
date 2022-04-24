package com.egpaid.employeeapp.merchentonboarding.injection

import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.merchentonboarding.view.MerchantOnBoardingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MerchantOnBoardingActivityBinding {

    @PerActivity
    @ContributesAndroidInjector(modules = [MerchantOnBoardingActivityModule::class])
    abstract fun bindMainActivityBinding(): MerchantOnBoardingActivity
}