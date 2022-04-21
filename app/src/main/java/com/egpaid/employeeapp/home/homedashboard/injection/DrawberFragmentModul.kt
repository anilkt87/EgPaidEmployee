package com.egpaid.employeeapp.home.homedashboard.injection

import android.content.Context
import com.egpaid.employeeapp.base.di.qualifier.ForFragment
import com.egpaid.employeeapp.base.di.scope.PerFragment
import com.egpaid.employeeapp.home.homedashboard.DrawberFragment
import dagger.Module
import dagger.Provides

@Module
class DrawberFragmentModul {
    @Provides
    @PerFragment
    @ForFragment
    fun provideContext(fragment: DrawberFragment): Context = fragment.requireContext()
}