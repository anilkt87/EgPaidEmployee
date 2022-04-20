package com.egpaid.employeeapp.signin.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.egpaid.employeeapp.base.domain.ResultData
import com.egpaid.employeeapp.base.base.schedulers.TrampolineSchedulerProvider
import com.egpaid.employeeapp.base.domain.model.ErrorModel
import com.egpaid.employeeapp.signin.repository.SignInRepo
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class GetSignInUseCaseTest {
    private lateinit var subject: GetSignInUseCaseImpl
    private var schedulerProvider = TrampolineSchedulerProvider()

    @Mock
    lateinit var signInRepo: SignInRepo
    @Mock
    lateinit var compositeDisposable: CompositeDisposable
    @Mock
    lateinit var getSignInLiveData: MutableLiveData<SignInUseCase.Result>
    @Mock
    lateinit var stateLiveDataObserver: Observer<SignInUseCase.Result>

    lateinit var response: ResultData

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        subject = GetSignInUseCaseImpl(compositeDisposable, getSignInLiveData, signInRepo, schedulerProvider)
        subject.resultLiveData().observeForever(stateLiveDataObserver)
    }

    @Before
    fun setUpRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false)
            }

            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }
        }

        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }


    @Test
    fun testGetPersonUseCases_getPerson_Success() {
        //GIVEN
        response = ResultData.fromData(Person())
        given(signInRepo.getPersonData(true)).willReturn(Observable.just(response))

        //WHEN
        subject.execute(true)

        //THEN
        verify(getSignInLiveData).value = SignInUseCase.Result.HasPersonData(response.data as Person)
    }

    @Test
    fun testGetPersonUseCases_getPerson_Error() {
        //GIVEN
        response = ResultData.fromError(errorModel = ErrorModel(key = Person.INSTANCE_ID, message = "", code = -1, responseCode = null, isServiceError = true))
        given(signInRepo.getPersonData(true)).willReturn(Observable.just(response))

        //WHEN
        subject.execute(true)

        //THEN
        verify(getSignInLiveData).value = SignInUseCase.Result.Error(ErrorModel(key = Person.INSTANCE_ID, message = "", code = -1, responseCode = null, isServiceError = true))
    }
}