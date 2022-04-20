package com.egpaid.employeeapp.base.di.module

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.room.Room
import com.egpaid.employeeapp.base.base.schedulers.BaseSchedulerProvider
import com.egpaid.employeeapp.base.base.schedulers.SchedulerProvider
import com.egpaid.employeeapp.base.base.schedulers.TestSchedulerProvider
import com.egpaid.employeeapp.base.base.schedulers.TrampolineSchedulerProvider
import com.egpaid.employeeapp.base.database.DbConstants
import com.egpaid.employeeapp.base.dataservice.database.ApplicationRoomDatabase
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.base.di.scope.PerApplication
import com.egpaid.employeeapp.signin.local.MonitorDailyDao
import com.egpaid.employeeapp.signin.local.MonitorWeeklyDao
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.TestScheduler

@Module
class ApplicationModule {
    @PerApplication
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @PerApplication
    fun provideBaseSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    @PerApplication
    fun providePersonDatabase(context: Context): ApplicationRoomDatabase {
        return Room.databaseBuilder(
            context,
            ApplicationRoomDatabase::class.java!!,
            DbConstants.PERSON_DB_NAME
        )
            .build()
    }


    @Provides
    @PerApplication
    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    @PerApplication
    fun provideTrampolineSchedulerProvider(): TrampolineSchedulerProvider {
        return TrampolineSchedulerProvider()
    }

    @Provides
    @PerApplication
    fun provideTestSchedulerProvider(): TestSchedulerProvider {
        return TestSchedulerProvider(TestScheduler())
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }


//    @PerApplication
//    @Provides
//    fun providePersonRepoRepo(api: RetrofitService, dao: PersonDao, schedulerProvider: BaseSchedulerProvider,  isTest: Boolean): PersonRepo = PersonRepoImpl(api, dao, schedulerProvider,  isTest)

//    @Provides
//    @PerApplication
//    fun providePersonDatabaseDao(database: ApplicationRoomDatabase): PersonDao = database.personDao()

    @Provides
    @PerApplication
    fun provideMonitorDatabaseDao(database: ApplicationRoomDatabase): MonitorDailyDao = database.monitorDao()

    @Provides
    @PerApplication
    fun provideMonitorWeeklyDatabaseDao(database: ApplicationRoomDatabase): MonitorWeeklyDao = database.monitorWeeklyDao()

    @Provides
    fun provideMediatorLiveData(): MediatorLiveData<BaseViewModel.State> {
        return MediatorLiveData<BaseViewModel.State>()
    }



}