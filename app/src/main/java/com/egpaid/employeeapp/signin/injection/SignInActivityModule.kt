package com.egpaid.employeeapp.signin.injection

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.egpaid.employeeapp.base.di.qualifier.ForActivity
import com.egpaid.employeeapp.base.di.scope.PerActivity
import com.egpaid.employeeapp.base.livedata.SingleLiveData
import com.egpaid.employeeapp.signin.domain.AppSettingUseCase
import com.egpaid.employeeapp.signin.domain.AppSettingUseCaseImpl
import com.egpaid.employeeapp.signin.domain.GetSignInUseCaseImpl
import com.egpaid.employeeapp.signin.domain.SignInUseCase
import com.egpaid.employeeapp.signin.repository.SignInRepo
import com.egpaid.employeeapp.signin.repository.SignInRepoImpl
import com.egpaid.employeeapp.signin.view.view.SigninActivity
import com.egpaid.employeeapp.signin.view.widget.SignInWidget
import com.egpaid.employeeapp.signin.view.widget.SignInWidgetImpl
import com.egpaid.employeeapp.signin.viewmodel.SignInViewModel
import com.egpaid.employeeapp.signin.viewmodel.SignInViewModelFactory
import com.egpaid.employeeapp.signin.viewmodel.SignInViewModelImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class SignInActivityModule {
    @Provides
    @PerActivity
    @ForActivity
    fun provideContext(activityInStore: SigninActivity): Context = activityInStore


    @Provides
    @PerActivity
    fun providePersonViewModel(
        signinActivity: SigninActivity,
        factory: SignInViewModelFactory
    ): SignInViewModel =
        ViewModelProvider(signinActivity, factory).get(SignInViewModel::class.java)

    @Provides
    @PerActivity
    fun bindPersonViewModel(personViewModelImpl: SignInViewModelImpl): ViewModel =
        personViewModelImpl

    @Provides
    @PerActivity
    fun providePersonWidget(personWidget: SignInWidgetImpl): SignInWidget = personWidget

    @Provides
    internal fun provideUseCase(useCase: GetSignInUseCaseImpl): SignInUseCase = useCase

    @Provides
    internal fun provideAppSettingUseCase(useCase: AppSettingUseCaseImpl): AppSettingUseCase = useCase

    @Provides
    internal fun provideMutableLiveData() = MutableLiveData<SignInUseCase.Callback>()

    @Provides
    fun providePersonRepository(personRepository: SignInRepoImpl): SignInRepo = personRepository

    @Provides
    @PerActivity
    fun provideOnClickedLiveData(): SingleLiveData<SignInWidget.CallToAction> = SingleLiveData()

    @PerActivity
    @Provides
    @Named("PerActivity")
    open fun provideInt(): String {
        return "hello"
    }

}