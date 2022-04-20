package com.egpaid.employeeapp.signin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.egpaid.employeeapp.base.domain.model.ErrorModel
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.signin.domain.SignInUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SignInViewModelTest {

    @Rule
    @JvmField
    val instantTaskRule = InstantTaskExecutorRule()

    private lateinit var subject: SignInViewModel

    @Mock
    private lateinit var getSignInCase: SignInUseCase

    @Mock
    private lateinit var personTypeStateLiveDataObserver: Observer<BaseViewModel.State>

    private val personTypeResponseLiveData: MediatorLiveData<BaseViewModel.State> = MediatorLiveData()

    private val getSignInTypeResponseLiveData: MutableLiveData<SignInUseCase.Result> = MutableLiveData()

    private lateinit var errorModel: ErrorModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        personTypeResponseLiveData.observeForever(personTypeStateLiveDataObserver)


        // GIVEN
        given(getSignInCase.resultLiveData()).willReturn(getSignInTypeResponseLiveData)


        // INITIALISE
        subject = SignInViewModelImpl(getSignInCase, personTypeResponseLiveData)

        // THEN
        then(getSignInCase).should().resultLiveData()
    }

    @Test
    fun test_Person_execute_success() {
        //GIVEN
        subject.getPersonData(true)

        // THEN
        then(personTypeStateLiveDataObserver).shouldHaveZeroInteractions()
    }

    @Test
    fun test_OffersType_null() {
        //GIVEN
        getSignInTypeResponseLiveData.value = null

        // THEN
        then(personTypeStateLiveDataObserver).shouldHaveZeroInteractions()
    }

    @Test
    fun test_OffersType_hasOffersType_Success() {

        val personData = Person()
        //GIVEN
        getSignInTypeResponseLiveData.value = SignInUseCase.Result.HasPersonData(personData)

        // THEN
        verify(personTypeStateLiveDataObserver).onChanged(BaseViewModel.State.Success(personData))
    }

    @Test
    fun test_PersonType_generic_error_Success() {
        //GIVEN
        errorModel = ErrorModel()
        getSignInTypeResponseLiveData.value = SignInUseCase.Result.Error(errorModel)

        // THEN
        verify(personTypeStateLiveDataObserver).onChanged(BaseViewModel.State.Error(errorModel))
    }

    @Test
    fun test_Person_generic_service_error_Success() {
        //GIVEN
        errorModel = ErrorModel(isServiceError = true)
        getSignInTypeResponseLiveData.value = SignInUseCase.Result.Error(errorModel)

        // THEN
        verify(personTypeStateLiveDataObserver).onChanged(BaseViewModel.State.Error(ErrorModel(isServiceError = true)))
    }


}