package com.egpaid.employeeapp.home.homedashboard.injection

import android.content.Context
import com.egpaid.employeeapp.base.di.qualifier.ForFragment
import com.egpaid.employeeapp.base.di.scope.PerFragment
import com.egpaid.employeeapp.home.homedashboard.HomeFragment
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentModul {
    @Provides
    @PerFragment
    @ForFragment
    fun provideContext(fragment: HomeFragment): Context = fragment.requireContext()
}