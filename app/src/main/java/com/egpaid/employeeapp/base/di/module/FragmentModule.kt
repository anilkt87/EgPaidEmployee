package com.egpaid.employeeapp.base.di.module

import com.egpaid.employeeapp.home.homedashboard.injection.DrawberFragmentBinding
import com.egpaid.employeeapp.home.monitor.injection.HomeFragmentBinding
import dagger.Module

@Module(includes = [
    HomeFragmentBinding::class, DrawberFragmentBinding::class])
abstract class FragmentModule