package com.egpaid.employeeapp.repostries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.egpaid.employeeapp.base.domain.ResultData
import com.egpaid.employeeapp.base.base.schedulers.TrampolineSchedulerProvider
import com.egpaid.employeeapp.base.dataservice.remote.RetrofitService
import com.egpaid.employeeapp.signin.repository.SignInRepoImpl
import com.egpaid.employeeapp.testutils.fileToGson
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


@RunWith(MockitoJUnitRunner::class)
class SignInRepoImplTest {
    private lateinit var subject: SignInRepoImpl
    private var schedulerProvider = TrampolineSchedulerProvider()
    @get:Rule
    var rule = InstantTaskExecutorRule()
    @Mock
    lateinit var personDao: PersonDao
    @Mock
    private lateinit var mockTestObserver: TestObserver<ResultData>
    @Mock
    private lateinit var mockListTestObserver: TestObserver<List<Person>>

    private lateinit var apiService: RetrofitService

    private var mockWebServer = MockWebServer()


    val result: ArrayList<ResultItem?>? = null
    @Before
    @Throws(Exception::class)
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        mockWebServer.start()
        apiService = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .baseUrl(mockWebServer.url("/"))
                .build()
                .create(RetrofitService::class.java)



        subject = SignInRepoImpl(apiService, personDao, schedulerProvider, isTestMode = true)
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

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }


    @Test
    @Throws(Exception::class)
    fun getPeerson_remote_Success() {
        //GIVEN
        val personData = fileToGson(Person::class.java, "person.json")
        val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(Gson().toJson(personData))
        mockWebServer.enqueue(response)

        //WHEN
        mockTestObserver = subject.getRemoteDataObservable().test()
        mockTestObserver.awaitTerminalEvent()

        //THEN
        mockTestObserver
                .assertNoErrors()
                .assertValue { resultData ->
                    val testPersonData = resultData.data as Person
                    testPersonData?.result?.get(0)?.firstName == "Herbert"
                }
        mockTestObserver.dispose()
    }

    @Test
    fun getPerson_fromDb_Success() {
        //GIVEN
        val personData = initLocalOffersData()
        given(personDao.getPerson()).willReturn(Single.just(arrayListOf(personData)))

        //WHEN
        mockListTestObserver = subject.getPersonDataFromDb().test()

        //THEN
        mockListTestObserver.assertComplete()
                .assertNoErrors()

        verify(personDao).getPerson()

    }


    private fun initLocalOffersData(): Person {
        val metadata = Meta(pageCount = 106, totalCount = 2114, message = "OK. Everything worked as expected.", code = 200, currentPage = 1, perPage = 20, rateLimit = RateLimit(limit = 30, remaining = 29, reset = 2), success = true)

        val resultItem = ResultItem(website = "https://kreiger.com/laborum-officia-et-consequatur-dolores-id.html", address = "84690 Lockman Mountains Suite 922\nNew Kelliechester, HI 62016-8147", id = "54", status = "inactive", dob = "2019-05-16", email = "leanne.lehner@example.org", firstName = "Herbert", gender = "male", lastName = "Sporer", links = null, phone = "1-975-717-1659")
        result?.add(resultItem)

        return Person(id = "INSTANCE_ID", meta = metadata, result = result)
    }


}