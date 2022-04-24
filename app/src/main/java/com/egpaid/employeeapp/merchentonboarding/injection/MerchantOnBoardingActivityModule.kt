package com.egpaid.employeeapp.merchentonboarding.injection

import android.content.Context
import com.egpaid.employeeapp.base.di.qualifier.ForActivity
import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.merchentonboarding.view.MerchantOnBoardingActivity
import dagger.Module
import dagger.Provides

@Module
class MerchantOnBoardingActivityModule {

    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(activity: MerchantOnBoardingActivity): Context = activity
}