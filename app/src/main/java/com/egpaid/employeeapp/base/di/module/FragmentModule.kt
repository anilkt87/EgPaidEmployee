package com.egpaid.employeeapp.base.di.module

import com.egpaid.employeeapp.books.injection.BooksFragmentBinding
import com.egpaid.employeeapp.home.homedashboard.injection.HomeFragmentBinding
import com.egpaid.employeeapp.home.monitor.injection.MonitorFragmentBinding
import dagger.Module

@Module(includes = [ BooksFragmentBinding::class,
    MonitorFragmentBinding::class, HomeFragmentBinding::class])
abstract class FragmentModule