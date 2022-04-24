package com.egpaid.employeeapp.base.di.module

import com.egpaid.employeeapp.applock.injection.AppLockActivityBinding
import com.egpaid.employeeapp.appsecurity.injection.AppSecurityActivityBinding
import com.egpaid.employeeapp.home.injection.MainActivityBinding
import com.egpaid.employeeapp.menuitem.injection.MenuActivityBinding
import com.egpaid.employeeapp.merchentonboarding.injection.MerchantOnBoardingActivityBinding
import com.egpaid.employeeapp.signin.injection.SiginActivityBinding
import dagger.Module


@Module(
    includes = [SiginActivityBinding::class,
        AppLockActivityBinding::class,
        MainActivityBinding::class, MenuActivityBinding::class,
        MerchantOnBoardingActivityBinding::class,
        AppSecurityActivityBinding::class]
)
abstract class ActivityModule