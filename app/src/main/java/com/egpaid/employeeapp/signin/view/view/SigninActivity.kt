package com.egpaid.employeeapp.signin.view.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.egpaid.employeeapp.R
import com.egpaid.employeeapp.base.extenstion.observe
import com.egpaid.employeeapp.base.viewmodel.BaseViewModel
import com.egpaid.employeeapp.base.widget.Widget
import com.egpaid.employeeapp.signin.entities.LoginRequestModel
import com.egpaid.employeeapp.signin.view.widget.SignInWidget
import com.egpaid.employeeapp.signin.viewmodel.SignInViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_signin.*
import javax.inject.Inject
import javax.inject.Named
import com.egpaid.employeeapp.BuildConfig
import com.egpaid.employeeapp.home.view.MainActivity
import android.content.Intent
import com.egpaid.employeeapp.applock.view.AppLockActivity

class SigninActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SignInViewModel

    @Inject
    lateinit var signInWidget: SignInWidget

    @Inject
    @Named("PerActivity")
    lateinit var pdpListWidget: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_signin)
        initWidget(container_sign_in)
        viewModel.apply {
            checkAlreadySignIn()
            observe(signInLiveData, ::getLoginResponse)
        }

    }

    private fun initWidget(view: View) {
        signInWidget.apply {
            initView(view)
            addWidget(this)
            show()
            observe(onClicked, ::onSignInRequest)
        }

    }

    private fun addWidget(widget: Widget) {
        lifecycle.addObserver(widget)
    }

    private fun onSignInRequest(callAction: SignInWidget.CallToAction) {
        when (callAction) {
            is SignInWidget.CallToAction.ValidateUser -> {
                viewModel.getLoginData(
                    LoginRequestModel(
                        callAction.loginRequest.loginId,
                        callAction.loginRequest.password
                    )
                )
            }
            is SignInWidget.CallToAction.EmptyFiled -> {
                Toast.makeText(this, "Please enter Mobile number & Password", Toast.LENGTH_LONG)
                    .show()
            }

        }
    }

    private fun getLoginResponse(loginstate: BaseViewModel.State) {
        when (loginstate) {
            is BaseViewModel.State.LoginSuccess -> {
                viewModel.getMyAppSideBarFromAPI()
            }
            is BaseViewModel.State.AlreadySignIn -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            is BaseViewModel.State.LoginError -> Toast.makeText(this, "Fail", Toast.LENGTH_LONG)
                .show()
            is BaseViewModel.State.Error -> Toast.makeText(this, "Fail", Toast.LENGTH_LONG).show()
            is BaseViewModel.State.MySideBarData -> {
                viewModel.getProfile()
            }
            is BaseViewModel.State.Loading -> {
                signInWidget.showLoading()
            }
            is BaseViewModel.State.AppSettingSuccess -> {
                val versionCode: Int = BuildConfig.VERSION_CODE
                val versionName: String = BuildConfig.VERSION_NAME
                if (versionName.equals(loginstate.appSettingResponse?.notification)) {
                    Log.d(
                        "APPSETTINGRESPONSE",
                        "Data" + loginstate.appSettingResponse?.notification
                    )
                } else {
                    Toast.makeText(this, "Please update version", Toast.LENGTH_LONG).show()
                }
            }

            is BaseViewModel.State.MyProfileResponse -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                signInWidget.hideProgressBar()
            }
            else -> {

            }
        }
    }

}
