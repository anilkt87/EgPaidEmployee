package com.egpaid.employeeapp.base.di.component

import android.app.Application
import com.egpaid.employeeapp.base.MainApplication
import com.egpaid.employeeapp.base.di.module.*
import com.egpaid.employeeapp.base.di.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule


@PerApplication
@Component(
    modules = [
        ApplicationModule::class,
        NetworkModule::class,
        PersistanceModule::class,
        ActivityModule::class,
        FragmentModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MainApplication)
}