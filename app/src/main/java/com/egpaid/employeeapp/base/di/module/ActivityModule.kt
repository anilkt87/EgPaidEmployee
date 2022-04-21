package com.egpaid.employeeapp.base.di.module

import com.egpaid.employeeapp.applock.injection.AppLockActivityBinding
import com.egpaid.employeeapp.home.injection.MainActivityBinding
import com.egpaid.employeeapp.signin.injection.SiginActivityBinding
import dagger.Module


@Module(includes = [ SiginActivityBinding::class,
    AppLockActivityBinding::class,
    MainActivityBinding::class])
abstract class ActivityModule