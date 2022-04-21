package com.egpaid.employeeapp.base.di.module

import com.egpaid.employeeapp.home.homedashboard.injection.DrawberFragmentBinding
import com.egpaid.employeeapp.home.monitor.injection.MonitorFragmentBinding
import dagger.Module

@Module(includes = [
    MonitorFragmentBinding::class, DrawberFragmentBinding::class])
abstract class FragmentModule